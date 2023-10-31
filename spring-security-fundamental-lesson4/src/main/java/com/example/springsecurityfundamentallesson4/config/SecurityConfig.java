package com.example.springsecurityfundamentallesson4.config;


import com.example.springsecurityfundamentallesson4.config.filter.ApiKeyFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${api-key}")
    private String key;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class);
                // authorization
                //.and().authenticationManager()   or  by adding a bean of type AuthenticationManager
                //.and().authenticationProvider() it doesn't override the AP, it adds one more to the collection
        http.httpBasic();
        return http.build();
    }


}
