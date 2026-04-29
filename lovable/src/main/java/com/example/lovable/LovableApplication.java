package com.example.lovable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;


@EntityScan(basePackages = "com.example.lovable")
@SpringBootApplication
public class LovableApplication {

	public static void main(String[] args) {
		SpringApplication.run(LovableApplication.class, args);
	}

}
