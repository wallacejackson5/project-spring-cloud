package com.example.projecteurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProjectEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEurekaServerApplication.class, args);
	}
}
