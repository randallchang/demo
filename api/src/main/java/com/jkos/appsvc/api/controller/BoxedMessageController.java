package com.jkos.appsvc.api.controller;

import com.jkos.appsvc.api.model.CommonPayload;
import com.jkos.appsvc.api.model.jkos_message.response.JkosMessageResponse;
import com.jkos.appsvc.api.service.BoxedMessageAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

import java.util.List;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
public class BoxedMessageController {

    private final BoxedMessageAppService boxedMessageAppService;

    @GetMapping("v1/my/boxedMessage/{page}")
    public CommonPayload<List<JkosMessageResponse>> myBoxedMessage(
            @RequestHeader(value = "authenticate", required = false) String authenticate,
            @RequestHeader(value = "memberId", required = false) String memberId,
            @Positive @PathVariable(value = "page") int page) {

        if (StringUtils.isNotBlank(authenticate)) {
            log.info("使用 authenticate 為 request, 查詢 MsgHub & RD2 資料");

            return boxedMessageAppService.getMyBoxedMessageByMemberId(authenticate, page);
        }

        // ValidationExceptionCode.REQUIRED.notBlank(memberId, "authenticate/memberId");
        log.info("使用 memberId 為 request, 僅回傳 MsgHub 資料");

        return boxedMessageAppService.getMyBoxedMessageByAuthenticate(memberId, page);
    }

}