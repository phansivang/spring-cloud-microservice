package com.microservice.producer;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class SpringWingApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringWingApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWingApplication.class, args);
	}

}
