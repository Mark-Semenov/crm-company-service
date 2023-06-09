package ru.bwforum.mark.company.service.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain config (ServerHttpSecurity http){
        http
                .csrf()
                .disable()
                .authorizeExchange()
                .anyExchange()
                .hasAuthority("SCOPE_crm.api")
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}
