package com.TaxCalculatorProject.TaxCalculatorBackEnd.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for local testing (can be enabled for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()  // Allow access to Swagger UI and API docs
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()  // Allow public access for GET requests to /api/products
                        .requestMatchers(HttpMethod.POST, "/api/products/**").permitAll()  // Allow public access for POST requests to /api/products
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").permitAll()  // Allow public access for PUT requests to /api/products
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").permitAll()  // Allow public access for DELETE requests to /api/products
                                .requestMatchers(HttpMethod.POST,"/api/tax/**").permitAll() // Allow public access for POST Requests to -> api/tax
                        //.anyRequest().authenticated()  // Secure all other endpoints
                );

        return httpSecurity.build();
    }
}
