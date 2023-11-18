package com.example.springsecurityfundamentallesson3.config.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MAAuthentication implements Authentication {

    private boolean isAuthenticate;
    private String name;

    public MAAuthentication(String name, boolean isAuthenticate) {
        this.name = name;
        this.isAuthenticate = isAuthenticate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticate;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticate = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }
}
