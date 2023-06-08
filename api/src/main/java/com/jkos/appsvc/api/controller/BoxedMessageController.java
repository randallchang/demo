package com.jkos.appsvc.api.controller;

import com.jkos.appsvc.api.constants.ApiError;
import com.jkos.appsvc.api.model.CommonPayload;
import com.jkos.appsvc.api.model.jkos_message.response.JkosMessageResponse;
import com.jkos.appsvc.api.service.BoxedMessageAppService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

import java.util.List;

@RestController
@RequestMapping("${api-version}")
@Validated
@RequiredArgsConstructor
public class BoxedMessageController {

    private final BoxedMessageAppService boxedMessageAppService;

    @GetMapping("/my/boxedMessage/{page}")
    public CommonPayload<List<JkosMessageResponse>> getMyBoxedMessagePage(
            @RequestHeader(value = "authenticate", required = false) String authenticate,
            @RequestHeader(value = "memberId", required = false) String memberId,
            @Positive @PathVariable(value = "page") int page) {

        if (StringUtils.isBlank(authenticate) && StringUtils.isBlank(memberId)) {
            throw ApiError.VALIDATION_FAILED.toException();
        }

        if (StringUtils.isNotBlank(authenticate)) {
            return CommonPayload.success(
                boxedMessageAppService.getMyBoxedMessageByMemberId(authenticate, page));
        }
        return CommonPayload.success(
            boxedMessageAppService.getMyBoxedMessageByAuthenticate(memberId, page));
    }

}