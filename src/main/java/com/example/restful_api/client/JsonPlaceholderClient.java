package com.example.restful_api.client;

import com.example.restful_api.client.dto.CreatePostRequest;
import com.example.restful_api.client.dto.CreatePostResponse;
import com.example.restful_api.config.client.HttpClientCommonConfig;
import lombok.experimental.UtilityClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.restful_api.client.JsonPlaceholderClient.Links.POST;

@FeignClient(name = "JsonPlaceholderClient",
        url = "${json-placeholder.url}",
        configuration = HttpClientCommonConfig.class)
public interface JsonPlaceholderClient {

    @PostMapping(POST)
    CreatePostResponse createPost(@RequestBody CreatePostRequest request);

    @UtilityClass
    class Links {
        public static final String POST = "/posts";
    }

}
