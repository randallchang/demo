package com.jkos.appsvc.api.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiRuntimeException extends RuntimeException {

    private final String code;
    private final String errorMessage;
}