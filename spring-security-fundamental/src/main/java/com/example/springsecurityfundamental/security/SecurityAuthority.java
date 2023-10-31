package com.example.springsecurityfundamental.security;

import com.example.springsecurityfundamental.entity.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final Authority authority;

    @Override
    public String getAuthority() {
        System.out.println("Calling SecurityAuthority " + authority.getName());
        return authority.getName();
    }
}
