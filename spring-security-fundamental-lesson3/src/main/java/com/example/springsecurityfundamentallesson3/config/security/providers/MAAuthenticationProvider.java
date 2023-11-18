package com.example.springsecurityfundamentallesson3.config.security.providers;

import com.example.springsecurityfundamentallesson3.config.security.authentication.MAAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MAAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("Calling MAAuthenticationProvider \n " + authentication.getAuthorities());

        Collection<GrantedAuthority> authorities = extractAuthorities();

        if("tester_user".equals(authentication.getName())){

            System.out.println("Calling MACAuthenticationProvider --> Ok \n ");
            return new MAAuthentication(authentication.getName(), true);

        } else {
            System.out.println("Calling MACAuthenticationProvider --> NOT Ok \n ");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(MAAuthentication.class);
    }

    private Collection<GrantedAuthority> extractAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String[] roles ={"ROLE_scadmin", "ROLE_guess"};
        for(String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
