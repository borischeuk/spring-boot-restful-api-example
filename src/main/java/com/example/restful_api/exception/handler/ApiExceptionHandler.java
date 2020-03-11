package com.example.restful_api.exception.handler;

import com.example.restful_api.exception.BadRequestException;
import com.example.restful_api.exception.RemoteServiceException;
import com.example.restful_api.exception.service.ExceptionHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final ExceptionHandlerService es;


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiExceptionInfo handleBadRequestException(BadRequestException e) {
        return es.handleException(e);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiExceptionInfo handleServletException(ServletRequestBindingException e) {
        return es.handleException(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiExceptionInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return es.handleException(e.getBindingResult());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiExceptionInfo handleUnhandledException(Throwable e) {
        return es.handleException(e);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiExceptionInfo handleBindException(BindException e) {
        return es.handleException(e.getBindingResult());
    }

    @ExceptionHandler(RemoteServiceException.class)
    @ResponseBody
    ResponseEntity<Object> handleRemoteServiceException(RemoteServiceException e) {
        return new ResponseEntity<>(es.handleException(e), e.getStatus());
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    @ResponseBody
    public ApiExceptionInfo handleUndeclaredThrowableExceptionException(UndeclaredThrowableException e) {
        return es.handleException(e);
    }
}
