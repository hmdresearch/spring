package com.example.springsecurityfundamentallesson4.config.providers;

import com.example.springsecurityfundamentallesson4.config.authentications.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("Calling ApiKeyAuthenticationProvider \n");

        ApiKeyAuthentication apiKeyAuthentication = (ApiKeyAuthentication) authentication;

        if(key.equals(apiKeyAuthentication.getKey())) {
            apiKeyAuthentication.setAuthenticated(true);
            return apiKeyAuthentication;
        }
        //throw new BadCredentialsException("Oh no!");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
