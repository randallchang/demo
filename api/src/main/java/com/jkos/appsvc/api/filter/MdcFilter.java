package com.jkos.appsvc.api.filter;

import com.jkos.appsvc.api.constants.Constants;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@WebFilter(urlPatterns = {"/*"})
@ConditionalOnWebApplication
public class MdcFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        injectTraceId((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        removeAllMDCValue();
        Filter.super.destroy();
    }

    private void injectTraceId(HttpServletRequest request) {
        String correlationIdDotnet = request.getHeader(Constants.REQ_CORRELATION_ID_DOTNET);
        String correlationIdJava = request.getHeader(Constants.REQ_CORRELATION_ID_JAVA);
        String correlationId = StringUtils.hasLength(correlationIdJava) ? correlationIdJava : correlationIdDotnet;
        if (!StringUtils.hasLength(correlationId)) {
            correlationId = UUID.randomUUID().toString();
        }
        request.setAttribute(Constants.REQ_CORRELATION_ID_DOTNET, correlationId);
        request.setAttribute(Constants.REQ_CORRELATION_ID_JAVA, correlationId);
        MDC.put(Constants.REQ_CORRELATION_ID_JAVA, correlationId);
    }

    private void removeAllMDCValue() {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        if (contextMap == null || contextMap.isEmpty()) {
            return;
        }
        for (String key : contextMap.keySet()) {
            MDC.remove(key);
        }
    }
}