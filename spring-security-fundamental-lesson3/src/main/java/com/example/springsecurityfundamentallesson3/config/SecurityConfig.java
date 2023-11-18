package com.example.springsecurityfundamentallesson3.config;



import com.example.springsecurityfundamentallesson3.config.security.filters.OPSWATAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    //private final CustomAuthenticationFilter customAuthenticationFilter;
    private final OPSWATAuthenticationFilter nacAuthenticationFilter;


    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("Calling UserDetailService \n");
        var userDetailsService = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("alice")
                .password("alice")
                .roles("guess")
                .build();

        var user2 = User.withUsername("bob")
                .password("bob")
                .roles("admin")
                .build();

        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.addFilterBefore(customAuthenticationFilter, CsrfFilter.class)
//            .authorizeRequests().anyRequest().authenticated();
        //http.httpBasic();
        http.authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter(nacAuthenticationFilter, BasicAuthenticationFilter.class);
                //.authenticationProvider(new NACAuthenticationProvider());
        http.httpBasic();
        return http.build();
    }
}
