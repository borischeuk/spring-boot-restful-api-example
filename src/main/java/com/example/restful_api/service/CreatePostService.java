package com.example.restful_api.service;

import com.example.restful_api.controller.dto.CreatePostRequest;
import com.example.restful_api.controller.dto.CreatePostResponse;

public interface CreatePostService {

    CreatePostResponse createPost(CreatePostRequest request);

}
