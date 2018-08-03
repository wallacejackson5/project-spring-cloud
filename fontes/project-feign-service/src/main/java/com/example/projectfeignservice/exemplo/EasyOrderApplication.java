package com.example.projectfeignservice.exemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EasyOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyOrderApplication.class, args);
	}
}
