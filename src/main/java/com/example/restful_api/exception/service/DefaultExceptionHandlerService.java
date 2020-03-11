package com.example.restful_api.exception.service;

import com.example.restful_api.exception.BadRequestException;
import com.example.restful_api.exception.GeneralApiException;
import com.example.restful_api.exception.handler.ApiExceptionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;

import java.util.List;

import static java.lang.String.join;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
public class DefaultExceptionHandlerService implements ExceptionHandlerService {

    private static final String DELIMITER = " : ";
    private static final String VALIDATION_ERROR = "Validation error";

    @Override
    public ApiExceptionInfo handleException(BindingResult bindingResult) {
        List<String> validationDetails = bindingResult.getFieldErrors().stream()
                .map(error -> join(DELIMITER, error.getField(), VALIDATION_ERROR))
                .collect(toList());

        return ApiExceptionInfo.builder()
                .timestamp(now())
                .status(BAD_REQUEST)
                .message(VALIDATION_ERROR)
                .error("Bad request")
                .detailedError(validationDetails)
                .build();
    }

    @Override
    public ApiExceptionInfo handleException(ServletRequestBindingException e) {
        return ApiExceptionInfo.builder()
                .timestamp(now())
                .status(BAD_REQUEST)
                .message(e.getMessage())
                .error(VALIDATION_ERROR)
                .build();
    }

    @Override
    public ApiExceptionInfo handleException(BadRequestException badRequest) {
        return ApiExceptionInfo.builder()
                .timestamp(now())
                .status(badRequest.getStatus())
                .message(badRequest.getMessage())
                .error("Bad request")
                .detailedError(badRequest.getErrorDetails())
                .build();
    }

    @Override
    public ApiExceptionInfo handleException(GeneralApiException ge) {
        log.warn(ge.getMessage(), ge);
        return ApiExceptionInfo.builder()
                .timestamp(now())
                .status(ge.getStatus())
                .message(ge.getMessage())
                .build();
    }

    @Override
    public ApiExceptionInfo handleException(Throwable e) {
        log.error("Unexpected error", e);

        return ApiExceptionInfo.builder()
                .timestamp(now())
                .status(INTERNAL_SERVER_ERROR)
                .message("Could not process request: " + e.getMessage())
                .error("Unexpected error")
                .build();
    }
}
