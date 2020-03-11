package com.example.restful_api.client.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePostResponse {

    private String id;

    private String userId;

    private String title;

    private String body;

}
