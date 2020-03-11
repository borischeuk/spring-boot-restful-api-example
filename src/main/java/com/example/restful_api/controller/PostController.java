package com.example.restful_api.controller;

import com.example.restful_api.controller.dto.CreatePostRequest;
import com.example.restful_api.controller.dto.CreatePostResponse;
import com.example.restful_api.service.CreatePostService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.restful_api.controller.util.Links.EXAMPLE_BASE_URL;
import static com.example.restful_api.controller.PostController.Links.POST;

@RestController
@RequiredArgsConstructor
@RequestMapping(EXAMPLE_BASE_URL)
public class PostController {

    private final CreatePostService createPostService;

    @PostMapping(POST)
    public CreatePostResponse createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
        return createPostService.createPost(createPostRequest);
    }

    @UtilityClass
    public static class Links {
        public static final String POST = "/v1/posts";
    }
}
