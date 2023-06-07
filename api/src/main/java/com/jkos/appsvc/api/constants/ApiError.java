package com.jkos.appsvc.api.constants;

import com.jkos.appsvc.api.exception.ApiRuntimeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiError {

    VALIDATION_FAILED("0001", "Validation failed."),
    DATA_NOT_FOUND("0008", "data not found."),
    DATA_ISSUE("0901", "data issue."),
    EXTERNAL_API_FAIL("1000", "access external api error."),
    EXTERNAL_API_TIMEOUT("1001", "access external api timeout"),
    EXTERNAL_API_UNREACHABLE("1002", "external api unreachable"),
    EXTERNAL_API_RESPONSE_NOT_EXPECTED("1003", "external api return unexpect data"),
    EXTERNAL_API_REPORT_ERROR("1004", "external api report error."),
    EXTERNAL_API_AUTH_ERROR("1005", "external api auth validation failed."),
    EXTERNAL_API_UNEXPECTED_ERROR("1999", "external api unexpected error."),
    UNSUPPORTED("7000", "unsupported."),
    DEPRECATED("7001", "deprecated"),
    MAINTAINING("7002", "maintaining"),
    UNEXPECTED("9999", "unexpected");

    private final String errorCode;
    private final String errorMessage;

    public ApiRuntimeException toException() {
        return new ApiRuntimeException(this.errorCode, this.errorMessage);
    }
}
