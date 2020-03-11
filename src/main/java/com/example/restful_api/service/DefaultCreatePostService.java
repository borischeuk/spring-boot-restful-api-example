package com.example.restful_api.service;

import com.example.restful_api.client.JsonPlaceholderClient;
import com.example.restful_api.client.dto.CreatePostResponse;
import com.example.restful_api.controller.dto.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultCreatePostService implements CreatePostService {

    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final CreatePostHelper createPostHelper;

    public com.example.restful_api.controller.dto.CreatePostResponse createPost(CreatePostRequest request) {
        CreatePostResponse response = jsonPlaceholderClient.createPost(createPostHelper.prepareCreatePostRequest(request));
        return createPostHelper.prepareCreatePostResponse(response);
    }

}
