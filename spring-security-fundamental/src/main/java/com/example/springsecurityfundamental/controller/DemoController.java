package com.example.springsecurityfundamental.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/demo")
    public String hello(){
        var au = SecurityContextHolder.getContext().getAuthentication();
        return "Hello ! Nice to meet you";
    }

}

