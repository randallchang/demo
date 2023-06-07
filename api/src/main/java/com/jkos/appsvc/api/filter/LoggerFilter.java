package com.jkos.appsvc.api.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.List;

public class LoggerFilter extends Filter<ILoggingEvent> {

    private static final List<String> EXCLUDED = List.of(
            "java.lang.reflect.Method",
            "sun.reflect",
            "org.apache.catalina",
            "org.springframework.aop",
            "org.springframework.security",
            "org.springframework.transaction",
            "org.springframework.web",
            "org.springframework.beans",
            "org.springframework.cglib",
            "net.sf.cglib",
            "org.apache.tomcat.util",
            "org.apache.coyote",
            "ByCGLIB",
            "BySpringCGLIB",
            "com.google.common.cache.LocalCache$",
            "javax.servlet.http.HttpServlet",
            "org.springframework.boot.actuate.metrics",
            "java.base/jdk.internal.reflect",
            "org.springframework.kafka.listener.KafkaMessageListenerContainer"
    );

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        String loggerName = iLoggingEvent.getLoggerName();
        if (EXCLUDED.stream().anyMatch(loggerName::startsWith)) {
            return FilterReply.DENY;
        } else {
            return FilterReply.NEUTRAL;
        }
    }

}
