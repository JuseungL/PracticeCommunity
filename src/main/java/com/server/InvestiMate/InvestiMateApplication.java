package com.server.InvestiMate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class InvestiMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestiMateApplication.class, args);
	}

}
