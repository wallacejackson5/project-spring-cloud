package com.example.projectconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ProjectConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectConfigServerApplication.class, args);
	}
}
