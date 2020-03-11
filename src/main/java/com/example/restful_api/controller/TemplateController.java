package com.example.restful_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    @GetMapping(value = "/template")
    public String getTemplate() {
        return "Hello";
    }

}
