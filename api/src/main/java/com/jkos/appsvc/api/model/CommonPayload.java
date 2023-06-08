package com.jkos.appsvc.api.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.jkos.appsvc.api.constants.Constants.DEFAULT_SUCCESS_MESSAGE;
import static com.jkos.appsvc.api.constants.Constants.RESULT_SUCCESS;
import static com.jkos.appsvc.api.constants.Constants.RESULT_TEMPLATE;

@Getter
@Builder
@AllArgsConstructor
public class CommonPayload<T> {

    @JsonProperty("Result")
    @Builder.Default
    private String result = RESULT_SUCCESS;

    @JsonProperty("Message")
    @Builder.Default
    private String message = DEFAULT_SUCCESS_MESSAGE;

    @JsonProperty("ResultObject")
    private T resultObject;

    public static CommonPayload<Void> success() {
        return CommonPayload.<Void>builder().build();
    }

    public static <T> CommonPayload<T> success(T resultObject) {

        return CommonPayload.<T>builder()
                .resultObject(resultObject)
                .build();
    }

    public static CommonPayload<Object> fail(String code, String message) {

        return CommonPayload.builder()
                .result(String.format(RESULT_TEMPLATE, code))
                .message(message)
                .build();
    }

    public static <T> CommonPayload<T> fail(
            String code, String message, T resultObject) {

        return CommonPayload.<T>builder()
                .result(String.format(RESULT_TEMPLATE, code))
                .message(message)
                .resultObject(resultObject)
                .build();
    }
}