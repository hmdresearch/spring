package com.example.springsecurityfundamentallesson3.config.security.managers;

import com.example.springsecurityfundamentallesson3.config.security.providers.MAAuthenticationProvider;
import com.example.springsecurityfundamentallesson3.config.security.providers.NACAuthenticationProvider;
import com.example.springsecurityfundamentallesson3.config.security.providers.SAMLAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OPSWATAuthenticationManager implements AuthenticationManager {

    private final NACAuthenticationProvider nacProvider;
    private final MAAuthenticationProvider maProvider;
    private final SAMLAuthenticationProvider samlProvider;

    public OPSWATAuthenticationManager(NACAuthenticationProvider nacProvider, MAAuthenticationProvider maProvider, SAMLAuthenticationProvider samlProvider) {
        this.nacProvider = nacProvider;
        this.maProvider = maProvider;
        this.samlProvider = samlProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("Calling OPSWATAuthenticationManager \n");

        if(nacProvider.supports(authentication.getClass())) {
            return nacProvider.authenticate(authentication);
        }

        if(maProvider.supports(authentication.getClass())) {
            return maProvider.authenticate(authentication);
        }

        if(samlProvider.supports(authentication.getClass())) {
            return samlProvider.authenticate(authentication);
        }

        throw new BadCredentialsException("Oh no!");
    }
}

