package com.jkos.appsvc.api.client;

import com.jkos.appsvc.api.config.FeignConfig;
import com.jkos.appsvc.api.model.msg_hub.response.MsgHubResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "MsgHubSvc",
    url = "${msg-hub.base-url}",
    configuration = FeignConfig.class
)
public interface MsgHubClient {

    @GetMapping(value = "/boxedMessages")
    MsgHubResponse getBoxedMessages(
        @RequestParam("target") int target,
        @RequestParam("memberId") String memberId,
        @RequestParam("page") int page,
        @RequestParam("pageSize") int pageSize);
}