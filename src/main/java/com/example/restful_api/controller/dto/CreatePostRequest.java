package com.example.restful_api.controller.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class CreatePostRequest {

    @NotNull
    private String userId;

    @NotNull
    private String title;

    @NotNull
    private String body;

}
