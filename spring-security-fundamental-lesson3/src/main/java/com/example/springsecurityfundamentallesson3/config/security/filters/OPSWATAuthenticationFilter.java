package com.example.springsecurityfundamentallesson3.config.security.filters;

import com.example.springsecurityfundamentallesson3.config.security.authentication.SAMLAuthentication;
import com.example.springsecurityfundamentallesson3.config.security.managers.OPSWATAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class OPSWATAuthenticationFilter extends OncePerRequestFilter {

    private final OPSWATAuthenticationManager opswatAuthenticationManager;

    public OPSWATAuthenticationFilter(OPSWATAuthenticationManager opswatAuthenticationManager) {
        this.opswatAuthenticationManager = opswatAuthenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Calling OPSWATAuthenticationFilter \n" + request.getSession().getId());

        // 1. create an authentication object which is not yet authenticated
        //UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("tester_user","password");

        //MAAuthentication authentication = new MAAuthentication("tester_user", false);

        Collection<GrantedAuthority> authorities = extractAuthorities();
        SAMLAuthentication authentication = new SAMLAuthentication("tester_user", null, true, authorities);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 2. delegate the authentication object to manager
        Authentication authenticationResult = opswatAuthenticationManager.authenticate(authentication);

        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain
        if(authenticationResult!=null && authenticationResult.isAuthenticated()) {
            // We should set SecurityContextHolder here, if not spring will not authen

            System.out.println("Calling OPSWATAuthenticationFilter --> Authentication is OK \n");
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        }
        // Remember to call next filter chain
        filterChain.doFilter(request, response);
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
