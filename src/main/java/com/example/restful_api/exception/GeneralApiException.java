package com.example.restful_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class GeneralApiException extends RuntimeException {

    private String errorReason;

    private Object errorDetails;

    public abstract HttpStatus getStatus();

    public GeneralApiException(String message) {
        super(message);
    }

    public GeneralApiException(String message, Object... o) {
        super(message);
        this.errorDetails = o;
    }

    public GeneralApiException(String message, Throwable cause) {
        super(message, cause);
        this.errorReason = cause.getMessage();
    }
    public GeneralApiException(String message, Throwable cause, Object... o) {
        super(message, cause);
        this.errorReason = cause.getMessage();
        this.errorDetails = o;
    }

}