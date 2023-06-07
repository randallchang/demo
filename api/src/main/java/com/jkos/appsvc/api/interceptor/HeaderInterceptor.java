package com.jkos.appsvc.api.interceptor;

import com.jkos.appsvc.api.constants.Constants;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String operator = request.getHeader("x-operator");
        MDC.put(Constants.REQ_HEADER_OPERATOR, operator);

        return true;
    }
}
