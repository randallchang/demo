package com.example.demo.controller;

import com.example.demo.client.HelloFeignClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testcontroller {

    @Autowired
    private HelloFeignClient helloFeignClient;

    @GetMapping("/ping")
    public ResponseEntity<Long> ping(@RequestParam String name) {

        System.out.println(name);

        return ResponseEntity.ok(System.currentTimeMillis());
    }

    @GetMapping("/ping2")
    public ResponseEntity<Long> ping2(@RequestParam String name) {

        Long result = null;

        try {
            result = helloFeignClient.ping(name);
        } catch (FeignException ex) {

        }

        return ResponseEntity.ok(result);
    }
}