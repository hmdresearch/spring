//package com.example.springsecurityfundamentallesson3.config.security.managers;
//
//import com.example.springsecurityfundamentallesson3.config.security.providers.CustomAuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthenticationManager  implements AuthenticationManager {
//
//    private final CustomAuthenticationProvider provider;
//    public CustomAuthenticationManager(CustomAuthenticationProvider provider) {
//        this.provider = provider;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        System.out.println("Calling CustomAuthenticationManager \n");
//        if(provider.supports(authentication.getClass())) {
//            return provider.authenticate(authentication);
//        }
//        throw new BadCredentialsException("Oh no!");
//    }
//}
