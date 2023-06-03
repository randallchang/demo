package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "HelloFeignClient", url = "${local.url}")
public interface HelloFeignClient {

    @GetMapping(value = "/ping123")
    Long ping(@RequestParam("name") String name);
}
