package com.example.springsecurityfundamentallesson3.config.security.providers;

import com.example.springsecurityfundamentallesson3.config.security.authentication.SAMLAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SAMLAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("Calling SAMLAuthenticationProvider \n " + authentication.getAuthorities());

        SAMLAuthentication samlAuthentication = (SAMLAuthentication) authentication;


        if(samlAuthentication.isSAMLResponse()){

            System.out.println("Calling SAMLAuthenticationProvider --> Ok \n ");

            Object details = samlAuthentication.getDetails();

            Collection<GrantedAuthority> authorities = samlAuthentication.getAuthorities();

            User userInfo = new User(samlAuthentication.getName(), "", true, true, true, true, authorities);

            SAMLAuthentication samlAuthenticationResult = new SAMLAuthentication(userInfo, null, authorities);

            samlAuthenticationResult.setDetails(details);
            return samlAuthenticationResult;

        } else {
            System.out.println("Calling SAMLAuthenticationProvider --> NOT Ok \n ");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(SAMLAuthentication.class);
    }


}
