package com.myweb.ecommerce;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

    @Value("${spring.data.mongodb.uri:NULL}")
    private String mongoUri;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

    @PostConstruct
    public void init() {
        System.out.println("Mongo URI = " + mongoUri);
    }
}
