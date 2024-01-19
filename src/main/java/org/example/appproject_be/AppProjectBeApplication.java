package org.example.appproject_be;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "https://assetmng-hwlee.koyeb.app/")})
@SpringBootApplication
public class AppProjectBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppProjectBeApplication.class, args);
	}

}
