package com.example.restful_api.exception;

import org.springframework.http.HttpStatus;

public class RemoteServiceException extends GeneralApiException {

    private HttpStatus status;

    public RemoteServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public RemoteServiceException(HttpStatus status, String errorReason, Throwable cause, Object... o) {
        super(errorReason, cause, o);
        this.status = status;
    }

    public RemoteServiceException(HttpStatus status, String message, Object... o) {
        super(message, o);
        this.status = status;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
