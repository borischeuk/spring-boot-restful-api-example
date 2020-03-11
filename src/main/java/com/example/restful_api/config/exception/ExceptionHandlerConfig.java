package com.example.restful_api.config.exception;

import com.example.restful_api.exception.service.DefaultExceptionHandlerService;
import com.example.restful_api.exception.service.ExceptionHandlerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandlerConfig {

    @Bean
    public ExceptionHandlerService productionExceptionService() {
        return new DefaultExceptionHandlerService();
    }

}
