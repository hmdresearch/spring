package com.example.springsecurityfundamentallesson4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/demo")
    public String demo(){
        return "Hello ! Nice to meet you";
    }
}
