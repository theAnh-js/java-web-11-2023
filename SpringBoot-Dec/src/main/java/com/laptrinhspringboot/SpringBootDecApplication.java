package com.laptrinhspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootDecApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDecApplication.class, args);
	}

}
