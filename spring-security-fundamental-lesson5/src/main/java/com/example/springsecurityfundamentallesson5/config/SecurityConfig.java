package com.example.springsecurityfundamentallesson5.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().hasAnyAuthority(("read"));
                //.and()
                //.authenticated();

        //            .anyRequest().authenticated()  // endpoint level authorization
        //        .anyRequest().permitAll()
        //        .anyRequest().denyAll()
        //        .anyRequest().hasAuthority("read")
        //        .anyRequest().hasAnyAuthority("read", "write")
        //        .anyRequest().hasRole("ADMIN")
        //        .anyRequest().hasAnyRole("ADMIN", "MANAGER")
        //        .anyRequest().access("isAuthenticated() and hasAuthority('read')")  // SpEL  --> authorization rules

        // matcher method + authorization rule
        // 1. which matcher methods should you use and how
        // 2. how to apply different authorization rules

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        System.out.println("Calling UserDetailService \n");

        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        // ROlE is a noun (ADMIN, MANAGER)
        // AUTHORITY is a verb (READ, WRITE)

        UserDetails user1 = User.withUsername("alice")
                .password(passwordEncoder().encode("alice"))
                .roles("GUESS")
                .authorities("read")
                //.authorities("ROLE_ADMIN")
                .build();

        UserDetails user2 = User.withUsername("bob")
                .password(passwordEncoder().encode("bob"))
                .roles("ADMIN")
                .authorities("write")
                .build();

        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
