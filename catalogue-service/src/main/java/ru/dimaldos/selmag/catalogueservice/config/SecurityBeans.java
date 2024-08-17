package ru.dimaldos.selmag.catalogueservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeans {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        .requestMatchers(HttpMethod.GET, "/catalogue-api/**").hasRole("SERVICE")
                        .requestMatchers(HttpMethod.PATCH, "/catalogue-api/**").hasRole("SERVICE")
                        .requestMatchers(HttpMethod.POST, "/catalogue-api/**").hasRole("SERVICE")
                        .requestMatchers(HttpMethod.DELETE, "/catalogue-api/**").hasRole("SERVICE"))
                .csrf(CsrfConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(oauth2ResourceServerConfigurer -> oauth2ResourceServerConfigurer
//                        .jwt(Customizer.withDefaults()))
//                .oauth2Client(Customizer.withDefaults())
                .build();
    }
}
