package com.jkos.appsvc.api;

import com.jkos.appsvc.api.model.CommonPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.TimeZone;

@EnableAspectJAutoProxy
@EnableFeignClients
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "com.jkos.appsvc")
@EnableCaching
@Slf4j
public class ApiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Taipei")));
        SpringApplication.run(ApiApplication.class, args);
    }

    @RestController
    public class TestConcroller {

        @GetMapping("ping")
        public ResponseEntity<CommonPayload<Object>> test() {
            log.info("success");
            return ResponseEntity.ok().body(CommonPayload.success());
        }
    }

}