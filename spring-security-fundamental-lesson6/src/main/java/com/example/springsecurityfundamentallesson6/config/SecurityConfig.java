package com.example.springsecurityfundamentallesson6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {

        System.out.println("Calling UserDetailService \n");

        var userDetailsService = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("scadmin")
                .password(passwordEncoder().encode("scadmin"))
                .roles("SCADMIN")
                .build();

        var user2 = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        System.out.println("Calling Custom securityFilterChain \n");


        http.httpBasic()
                .and()
                .authorizeRequests()
                //.anyRequest()
                .requestMatchers("/*/*/test1").hasAnyRole( "SCADMIN")
                .requestMatchers("/*/*/test2").hasAnyRole("ADMIN","SCADMIN");
                //.requestMatchers("/api/v1/**").hasAnyRole("ADMIN","SCADMIN");
                //.authenticated();

        return http.build();
    }


}
