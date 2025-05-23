package com.example.ceybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class CeybankApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeybankApplication.class, args);
	}

}
