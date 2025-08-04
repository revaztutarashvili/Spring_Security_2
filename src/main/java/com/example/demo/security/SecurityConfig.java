package com.example.demo.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
    @Qualifier("UsernameAuthenticationFilter")OncePerRequestFilter authenticationProcessingFilter) throws Exception{
        httpSecurity
                .addFilterBefore(authenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(x ->x.requestMatchers("/login").permitAll().anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
