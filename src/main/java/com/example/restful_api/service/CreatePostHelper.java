package com.example.restful_api.service;

import com.example.restful_api.client.dto.CreatePostRequest;
import com.example.restful_api.controller.dto.CreatePostResponse;
import org.springframework.stereotype.Component;

@Component
public class CreatePostHelper {

    public CreatePostRequest prepareCreatePostRequest(com.example.restful_api.controller.dto.CreatePostRequest request) {
        return CreatePostRequest.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .body(request.getBody())
                .build();
    }

    public CreatePostResponse prepareCreatePostResponse(com.example.restful_api.client.dto.CreatePostResponse response) {
        return CreatePostResponse.builder()
                .id(response.getId())
                .build();
    }

}
