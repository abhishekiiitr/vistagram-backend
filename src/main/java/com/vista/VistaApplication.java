package com.vista;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VistaApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();  // looks inside src/main/resources automatically
		System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGO_URI"));
		SpringApplication.run(VistaApplication.class, args);
	}

}
