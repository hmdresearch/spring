package com.example.springsecurityfundamentallesson3.controller;

import com.example.springsecurityfundamentallesson3.service.NACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("hasRole('scadmin')")
public class DemoController {

    @Autowired
    NACService nacService;

    @GetMapping("/demo")
    public String hello() {
        nacService.doSth();
        return "Hello ! Nice to meet you";
    }
}
