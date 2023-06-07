package com.jkos.appsvc.api.handler;


import com.jkos.appsvc.api.constants.ApiError;
import com.jkos.appsvc.api.exception.ApiRuntimeException;
import com.jkos.appsvc.api.model.CommonPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
@Slf4j
@ConditionalOnWebApplication
public class ApiExceptionHandler {

    @ExceptionHandler(ApiRuntimeException.class)
    public ResponseEntity<CommonPayload<Object>> handleApiRuntimeException(
            ApiRuntimeException ex) {

        return getResponseEntity(ex.getCode(), ex.getErrorMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonPayload<Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        log.error("method argument not valid: " + ex.getMessage());

        return getResponseEntity(
            ApiError.VALIDATION_FAILED.getErrorCode(),
            ApiError.VALIDATION_FAILED.getErrorMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CommonPayload<Object>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        log.error(ex.getMessage());

        return getResponseEntity(
            ApiError.DATA_ISSUE.getErrorCode(),
            ApiError.DATA_ISSUE.getErrorMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonPayload<Object>> handleException(Exception ex) {

        log.error(ex.getMessage());

        return getResponseEntity(
            ApiError.UNEXPECTED.getErrorCode(),
            ApiError.UNEXPECTED.getErrorMessage());
    }

    private ResponseEntity<CommonPayload<Object>> getResponseEntity(
            String errorCode, String errorMessage) {

        return ResponseEntity.ok(CommonPayload.fail(errorCode, errorMessage));
    }
}