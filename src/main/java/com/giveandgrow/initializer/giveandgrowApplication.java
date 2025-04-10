package com.giveandgrow.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.giveandgrow")
@EnableJpaRepositories(basePackages = "com.giveandgrow.infrastructure.repositories")
@EntityScan(basePackages = "com.giveandgrow.infrastructure.entities")
public class giveandgrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(giveandgrowApplication.class, args);
	}

}

