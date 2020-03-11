package com.example.restful_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@SpringBootApplication
@EnableFeignClients
public class RestfulApiExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiExampleApplication.class, args);
    }

}
