package com.dariphion.notee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class NoteeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteeApplication.class, args);
	}

}
