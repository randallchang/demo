package com.jkos.appsvc.api.config;

import com.jkos.appsvc.api.constants.Constants;
import feign.Logger;
import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    RequestInterceptor bearerAuthRequestInterceptor() {
        return requestTemplate ->
            requestTemplate.header(Constants.REQ_CORRELATION_ID_JAVA,
                MDC.get(Constants.REQ_CORRELATION_ID_JAVA));
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
