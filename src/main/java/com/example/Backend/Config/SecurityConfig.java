package com.example.Backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.Backend.Filter.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    // Constructor
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()   // allow token API
                .anyRequest().authenticated()       // secure others
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // JwtFilter will execute before UsernamePasswordAuthenticationFilter

        return http.build();
    }
}

