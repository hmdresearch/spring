//package com.example.springsecurityfundamentallesson3.config.security.filters;
//
//import com.example.springsecurityfundamentallesson3.config.security.authentication.CustomAuthentication;
//import com.example.springsecurityfundamentallesson3.config.security.managers.CustomAuthenticationManager;
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.expression.spel.CodeFlow;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class CustomAuthenticationFilter extends OncePerRequestFilter {
//
//    private final CustomAuthenticationManager customAuthenticationManager;
//
//    public CustomAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager) {
//        this.customAuthenticationManager = customAuthenticationManager;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("Calling CustomAuthenticationFilter \n");
//
//        // 1. create an authentication object which is not yet authenticated
//        String key = String.valueOf(request.getHeader("api-key"));
//        CustomAuthentication ca = new CustomAuthentication(false, key);
//
//        // 2. delegate the authentication object to manager
//        var au = customAuthenticationManager.authenticate(ca);
//
//        // 3. get back the authentication from the manager
//        // 4. if the object is authenticated then send request to the next filter in the chain
//        if(au.isAuthenticated()) {
//            // We should set SecurityContextHolder here, if not spring will not authen
//            SecurityContextHolder.getContext().setAuthentication(ca);
//
//            // Remember to call next filter chain
//            filterChain.doFilter(request, response);
//        }
//
//    }
//}
