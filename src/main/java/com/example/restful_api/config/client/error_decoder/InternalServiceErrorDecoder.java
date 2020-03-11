package com.example.restful_api.config.client.error_decoder;

import com.example.restful_api.exception.RemoteServiceException;
import com.example.restful_api.exception.handler.ApiExceptionInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
@Slf4j
public class InternalServiceErrorDecoder implements ErrorDecoder {

    private static final TypeReference<ApiExceptionInfo> EXCEPTION_INFO_TYPE_REFERENCE = new TypeReference<ApiExceptionInfo>() {
    };

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        ApiExceptionInfo apiExceptionInfo = createExceptionInfoFrom(response);

        return new RemoteServiceException(apiExceptionInfo.getStatus(),
                apiExceptionInfo.getMessage(),
                apiExceptionInfo.getDetailedError());
    }


    private ApiExceptionInfo createExceptionInfoFrom(Response response) {
        String body = getBody(response);

        try {
            ApiExceptionInfo apiExceptionInfo = objectMapper.readValue(body, EXCEPTION_INFO_TYPE_REFERENCE);
            checkNotNull(apiExceptionInfo.getStatus());
            checkNotNull(apiExceptionInfo.getMessage());
            return apiExceptionInfo;
        } catch (Exception e) {
            log.debug("Response format is invalid: {}", response);
        }

        return ApiExceptionInfo.builder()
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .message(format("External service status: '%s', reason: '%s'", response.status(), response.reason()))
                .detailedError(response.toString())
                .build();
    }

    private String getBody(Response response) {
        try {
            return StreamUtils.copyToString(response.body().asInputStream(), UTF_8);
        } catch (IOException e) {
            log.debug("ErrorDecode is unable to read response {}", response);
        }
        return "";
    }
}
