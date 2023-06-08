package com.jkos.appsvc.api.service;

import com.jkos.appsvc.api.client.JkosUapiClient;
import com.jkos.appsvc.api.client.MsgHubClient;
import com.jkos.appsvc.api.constants.ApiError;
import com.jkos.appsvc.api.constants.BoxedMessageTargetType;
import com.jkos.appsvc.api.constants.Constants;
import com.jkos.appsvc.api.model.CommonPayload;
import com.jkos.appsvc.api.model.jkos_message.response.BehaviorParameter;
import com.jkos.appsvc.api.model.jkos_message.response.JkosMessageResponse;
import com.jkos.appsvc.api.model.msg_hub.response.BoxedMessage;
import com.jkos.appsvc.api.model.msg_hub.response.MsgHubResponse;
import com.jkos.appsvc.api.util.AuthenticateUtil;
import com.jkos.appsvc.api.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoxedMessageAppService {

    private final MsgHubClient msgHubClient;
    private final JkosUapiClient jkosUapiClient;

    @Value("${jkos-uapi.myMessageDownTime}")
    private String myMessageDownTime;

    @Value("${authenticate.oldUapiToken.pwdAesKey}")
    private String pwdAesKey;

    @Value("${authenticate.oldUapiToken.pwdAesIV}")
    private String pwdAesIV;

    public List<JkosMessageResponse> getMyBoxedMessageByMemberId(String memberId, int page) {

        MsgHubResponse msgHubResponse =
            msgHubClient.getBoxedMessages(
                BoxedMessageTargetType.USER.getValue(),
                memberId,
                page,
                Constants.DEFAULT_PAGE_SIZE);

        if (Constants.MSG_HUB_SUCCESS_CODE.equals(msgHubResponse.getCode())) {
            List<BoxedMessage> dataList =
                msgHubClient.getBoxedMessages(
                    BoxedMessageTargetType.USER.getValue(),
                    memberId,
                    page,
                    Constants.DEFAULT_PAGE_SIZE).getData();

            return CollectionUtils.isEmpty(dataList)
                    ? Collections.emptyList()
                    : dataList.stream()
                        .map(data -> convert(memberId, data))
                        .collect(Collectors.toList());
        }

        log.error("[MsgHub] access api error.");
        throw ApiError.EXTERNAL_API_FAIL.toException();
    }

    public List<JkosMessageResponse> getMyBoxedMessageByAuthenticate(
            String authenticate, int page) {

        String memberId =
            AuthenticateUtil
                .decrypt(authenticate, pwdAesKey, pwdAesIV)
                .get(Constants.MEMBER_ID_PROPERTY_KEY);

        if (StringUtils.isBlank(memberId)) {
            log.error("token unvalid.");
            throw ApiError.EXTERNAL_API_AUTH_ERROR.toException();
        }

        List<JkosMessageResponse> jkosMessageResponseList =
            getMyBoxedMessageByMemberId(memberId, page);

        jkosMessageResponseList =
            hardCodeBeforeUapiDeprecated(
                jkosMessageResponseList,
                authenticate,
                page);

        return jkosMessageResponseList;
    }

    private List<JkosMessageResponse> hardCodeBeforeUapiDeprecated(
            List<JkosMessageResponse> jkosMessageResponseList,
            String authenticate,
            int page) {

        if (LocalDateTime.now().isBefore(
                    LocalDateTime.parse(myMessageDownTime))
                && (CollectionUtils.isEmpty(jkosMessageResponseList)
                    || jkosMessageResponseList.size() < Constants.DEFAULT_PAGE_SIZE)) {

            CommonPayload<List<JkosMessageResponse>> response =
                jkosUapiClient.getJkosMessages(authenticate, page);

            if (!Constants.RESULT_SUCCESS.equals(response.getResult())) {
                log.error("[Uapi] access api error.");
                throw ApiError.EXTERNAL_API_FAIL.toException();
            }

            jkosMessageResponseList = response.getResultObject();
        }

        return jkosMessageResponseList;
    }

    private JkosMessageResponse convert(String memberId, BoxedMessage boxedMessage) {

        JkosMessageResponse jkosMessageResponse =
            ConvertUtil.deepCloneInstantiateClass(
                boxedMessage,
                JkosMessageResponse.class);
        BehaviorParameter behaviorParameter =
            jkosMessageResponse.getBehaviorParameter();

        if (behaviorParameter != null) {
            behaviorParameter.setPushType(boxedMessage.getPushType());
        }

        jkosMessageResponse.setMemberId(memberId);

        return jkosMessageResponse;
    }
}