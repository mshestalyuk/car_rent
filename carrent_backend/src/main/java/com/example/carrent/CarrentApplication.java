package com.example.carrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.carrent.model"}) 
public class CarrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrentApplication.class, args);
	}

}
