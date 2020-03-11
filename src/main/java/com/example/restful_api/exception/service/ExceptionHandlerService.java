package com.example.restful_api.exception.service;

import com.example.restful_api.exception.BadRequestException;
import com.example.restful_api.exception.GeneralApiException;
import com.example.restful_api.exception.handler.ApiExceptionInfo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;

public interface ExceptionHandlerService {

    ApiExceptionInfo handleException(BindingResult bindingResult);

    ApiExceptionInfo handleException(ServletRequestBindingException e);

    ApiExceptionInfo handleException(BadRequestException badRequest);

    ApiExceptionInfo handleException(GeneralApiException ge);

    ApiExceptionInfo handleException(Throwable e);
}
