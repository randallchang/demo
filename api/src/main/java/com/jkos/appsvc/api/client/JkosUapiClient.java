package com.jkos.appsvc.api.client;

import com.jkos.appsvc.api.config.FeignConfig;
import com.jkos.appsvc.api.model.CommonPayload;
import com.jkos.appsvc.api.model.jkos_message.response.JkosMessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
    name = "JkosUapi",
    url = "${jkos-uapi.base-url}",
    configuration = FeignConfig.class)
public interface JkosUapiClient {

    @GetMapping("/My/JKOSMessage/{page}")
    CommonPayload<List<JkosMessageResponse>> getJkosMessages(
        @RequestHeader("Authenticate") String authenticate,
        @RequestParam("page") int page);
}