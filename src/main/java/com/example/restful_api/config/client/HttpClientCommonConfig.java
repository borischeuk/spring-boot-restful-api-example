package com.example.restful_api.config.client;

import com.example.restful_api.config.client.error_decoder.InternalServiceErrorDecoder;
import com.example.restful_api.config.properties.RetryProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

@Slf4j
public class HttpClientCommonConfig {

    @Bean
    public Logger.Level httpClientLoggingLevel(@Value("${feign.logging.level:BASIC}")
                                                       String feignLoggingLevel) {
        log.debug("Setting feign logging level to {}", feignLoggingLevel);

        return Logger.Level.valueOf(feignLoggingLevel);
    }

    @Bean
    public Decoder jsonDecoder(ObjectFactory<HttpMessageConverters> factory) {
        return new SpringDecoder(factory);
    }

    @Bean
    public Encoder jsonEncoder(ObjectFactory<HttpMessageConverters> factory) {
        return new SpringEncoder(factory);
    }


    @Bean
    public InternalServiceErrorDecoder internalServiceJsonErrorDecoder(ObjectMapper objectMapper) {
        return new InternalServiceErrorDecoder(objectMapper);
    }

    @Bean
    public Retryer retryer(RetryProperties retryProperties) {
        return new Retryer.Default(retryProperties.backOffInitialInterval,
                retryProperties.backOffMaxInterval,
                retryProperties.retryMaxAttempts);
    }

    @Bean
    public RetryProperties retryProperties() {
        return new RetryProperties();
    }
}
