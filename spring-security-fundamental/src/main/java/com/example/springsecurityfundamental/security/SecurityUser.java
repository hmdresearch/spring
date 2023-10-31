package com.example.springsecurityfundamental.security;

import com.example.springsecurityfundamental.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final User user;

    @Override
    public String getPassword() {
        System.out.println("Calling SecurityUser user.getPassword() " + user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("Calling SecurityUser user.getUsername() " + user.getUsername());
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Calling SecurityUser GrantedAuthority \n");
        return user.getAuthorities()
                .stream()
                .map(SecurityAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
