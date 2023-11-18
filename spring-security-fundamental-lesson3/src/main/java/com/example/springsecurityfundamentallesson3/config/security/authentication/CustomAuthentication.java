package com.example.springsecurityfundamentallesson3.config.security.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthentication implements Authentication {

    private boolean authentication;
    private String key;

    Collection<GrantedAuthority> authorities;

    public CustomAuthentication(String key) {
        this.key = key;
    }

    public CustomAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    public CustomAuthentication(boolean authentication, String key) {
        this.authentication = authentication;
        this.key = key;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return authentication;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                this.authentication = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
