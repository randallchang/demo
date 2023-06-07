package com.jkos.appsvc.api.service;

import com.jkos.appsvc.api.client.JkosUapiClient;
import com.jkos.appsvc.api.client.MsgHubClient;
import com.jkos.appsvc.api.constants.Constants;
import com.jkos.appsvc.api.model.CommonPayload;
import com.jkos.appsvc.api.model.jkos_message.response.JkosMessageResponse;
import com.jkos.appsvc.api.model.msg_hub.response.BoxedMessage;
import com.jkos.appsvc.api.utils.AuthenticateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoxedMessageAppService {

    private final MsgHubClient msgHubApiManager;
    private final JkosUapiClient jkosUapiManager;

    // @Value("${jkos.uapi.myJkosMessageOfflineMonth}")
    private String myJkosMessageOfflineMonth;

    @Value("${authenticate.oldUapiToken.pwdAesKey}")
    private String pwdAesKey;

    @Value("${authenticate.oldUapiToken.pwdAesIV}")
    private String pwdAesIV;

    public CommonPayload<List<JkosMessageResponse>> getMyBoxedMessageByMemberId(String memberId, int page) {
        int pageSize = 10;
        List<BoxedMessage> boxedMessageList = msgHubApiManager.getBoxedMessagesByMemberId(memberId, page, pageSize);
        return null;//new MyBoxedMessageResponse(memberId, boxedMessageList);
    }

    public CommonPayload<List<JkosMessageResponse>> getMyBoxedMessageByAuthenticate(String authenticate, int page) {
        int pageSize = 10;
        String memberId = AuthenticateUtils.decrypt(authenticate, pwdAesKey, pwdAesIV).get(Constants.MEMBER_ID_PROPERTY_KEY);
        if (StringUtils.isBlank(memberId)) {
            log.error("此 token 無法解出 memberId");
            return null;//new MyBoxedMessageResponse(2, "Token 認證失敗", null);
        }

        memberId = memberId.substring(1, memberId.length() - 1);
        List<BoxedMessage> boxedMessageList = msgHubApiManager.getBoxedMessagesByMemberId(memberId, page, pageSize);

        if (isFullFillPage(boxedMessageList, pageSize)) {
            log.info("MsgHub 資訊足夠, 可回傳");
            return null;//new MyBoxedMessageResponse(memberId, boxedMessageList);
        }

        if (isRD2ApiOffline()) {
            log.info("RD2 api 已下線, 僅使用 MsgHub response");
            return null;//new MyBoxedMessageResponse(memberId, boxedMessageList);
        } else {
            log.info("MsgHub 資訊不足, 使用 RD2 資料回傳");
            return jkosUapiManager.getJkosMessages(authenticate, page);
        }
    }

    private boolean isRD2ApiOffline() {
        YearMonth now = YearMonth.now();

        YearMonth rd2ApiOfflineMonth =
            YearMonth.parse(
                myJkosMessageOfflineMonth,
                DateTimeFormatter.ofPattern(Constants.CUSTOM_YEAR_MONTH_FORMAT));

        log.info("現在月份 : {}, RD2 My JkosMessage API 最後支援月份 : {}",
            now.format(DateTimeFormatter.ofPattern(Constants.CUSTOM_YEAR_MONTH_FORMAT)),
            myJkosMessageOfflineMonth);

        return now.isAfter(rd2ApiOfflineMonth);
    }

    private boolean isFullFillPage(List<BoxedMessage> list, int pageSize) {
        return !CollectionUtils.isEmpty(list) && list.size() == pageSize;
    }
}