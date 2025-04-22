package com.example.ceybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorAwareConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Replace this with logic to get real user from Spring Security
        return () -> Optional.of("admin"); // ← hardcoded for now
    }
}

