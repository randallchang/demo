package com.jkos.appsvc.api.client;

import com.jkos.appsvc.api.model.msg_hub.response.BoxedMessage;
import com.jkos.appsvc.api.model.msg_hub.response.MsgHubResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MsgHubClient", url = "${msg-hub.base-url}")
public interface MsgHubClient {

    @GetMapping(value = "/boxedMessages")
    MsgHubResponse getBoxedMessages(
        @RequestParam("target") int target,
        @RequestParam("memberId") String memberId,
        @RequestParam("page") int page,
        @RequestParam("pageSize") int pageSize);

    // TO DO: remove
    @GetMapping(value = "/boxedMessages")
    List<BoxedMessage> getBoxedMessagesByMemberId(@RequestParam String memberId, @RequestParam int page, @RequestParam  int pageSize);
}