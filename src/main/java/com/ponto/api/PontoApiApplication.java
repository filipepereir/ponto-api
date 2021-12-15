package com.ponto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class PontoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoApiApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
