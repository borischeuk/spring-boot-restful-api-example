package com.example.restful_api.exception.handler;

import com.example.restful_api.exception.GeneralApiException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApiExceptionInfo {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String error;
    private Object detailedError;

    public ApiExceptionInfo(GeneralApiException ge) {
        this.timestamp = now();
        this.status = ge.getStatus();
        this.message = ge.getMessage();
        this.error = ge.getErrorReason();
        this.detailedError = ge.getErrorDetails();
    }
}
