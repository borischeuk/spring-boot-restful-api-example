package com.example.restful_api.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends GeneralApiException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object... o) {
        super(message, o);
    }

    public BadRequestException(String errorReason, Throwable cause) {
        super(errorReason, cause);
    }

    public BadRequestException(String errorReason, Throwable cause, Object... o) {
        super(errorReason, cause, o);
    }

    @Override
    public HttpStatus getStatus() {
        return BAD_REQUEST;
    }
}
