package com.example.springsecurityfundamentallesson6.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/test1")
    public String test1(){
        return "This is test 1";
    }

    @GetMapping("/test2")
    public String test2(){
        return "This is test 2";
    }

}
