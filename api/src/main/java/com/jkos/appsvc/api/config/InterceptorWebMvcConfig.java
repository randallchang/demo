package com.jkos.appsvc.api.config;

import com.jkos.appsvc.api.constants.Constants;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class InterceptorWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(
            new HandlerInterceptor() {
                @Override
                public boolean preHandle(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Object handler) throws Exception {

                    String operator = request.getHeader("x-operator");
                    MDC.put(Constants.REQ_HEADER_OPERATOR, operator);

                    return true;
                }});
    }
}
