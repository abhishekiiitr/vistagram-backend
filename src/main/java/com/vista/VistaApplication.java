package com.vista;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VistaApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();  // looks inside src/main/resources automatically
		System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));
		SpringApplication.run(VistaApplication.class, args);
	}

}
