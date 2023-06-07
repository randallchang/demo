package com.jkos.appsvc.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jkos.appsvc.api.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.MDC;

@Getter
@Builder
@AllArgsConstructor
public class CommonPayload<T> {

    private static final String ENV_CODE = "3";
    private static final String SERVICE_CODE = "JA";
    private static final String RESULT_TEMPLATE = "%s-%s-%s";
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String RESULT_SUCCESS = "0001";

    @JsonProperty("Result")
    private String result;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("ResultObject")
    private T resultObject;

    public static <T> CommonPayload<T> success() {
        return success(null);
    }

    public static <T> CommonPayload<T> success(T resultObject) {

        return CommonPayload.<T>builder()
                .result(RESULT_SUCCESS)
                .message(DEFAULT_SUCCESS_MESSAGE)
                .resultObject(resultObject)
                .build();
    }

    public static CommonPayload<Object> fail(String code, String message) {

        return CommonPayload.builder()
                .result(String.format(
                        RESULT_TEMPLATE,
                        ENV_CODE,
                        SERVICE_CODE,
                        code))
                .message(message)
                .build();
    }

    public static <T> CommonPayload<T> fail(
            String code, String message, T resultObject) {

        return CommonPayload.<T>builder()
                .result(String.format(RESULT_TEMPLATE, ENV_CODE, SERVICE_CODE, code))
                .message(message)
                .resultObject(resultObject)
                .build();
    }
}