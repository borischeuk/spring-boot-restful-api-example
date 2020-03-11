package com.example.restful_api.config;

import com.example.restful_api.config.exception.ExceptionHandlerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@Import(value = {
        ExceptionHandlerConfig.class
})
@EnableRetry
public class CommonConfig {
}
