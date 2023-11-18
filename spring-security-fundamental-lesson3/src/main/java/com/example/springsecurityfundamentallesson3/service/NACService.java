package com.example.springsecurityfundamentallesson3.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NACService {

    public void doSth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            System.out.println("Calling NACService doSth --> Ok \n " + auth.getName());
        }else {
            System.out.println("Calling NACService doSth --> NOT Ok \n");
        }
    }
}
