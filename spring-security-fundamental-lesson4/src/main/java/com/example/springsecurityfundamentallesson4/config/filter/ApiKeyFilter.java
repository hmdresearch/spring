package com.example.springsecurityfundamentallesson4.config.filter;

import com.example.springsecurityfundamentallesson4.config.authentications.ApiKeyAuthentication;
import com.example.springsecurityfundamentallesson4.config.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Calling ApiKeyFilter \n");
        CustomAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(key);

        var requestKey = request.getHeader("api-key");

        if(requestKey == null || "null".equals(requestKey)) {
            filterChain.doFilter(request, response);
        }
        var auth = new ApiKeyAuthentication(requestKey);

        var au = customAuthenticationManager.authenticate(auth);
        if(au != null && au.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(au);
        }
        filterChain.doFilter(request, response);
    }
}
