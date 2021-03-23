package com.mtotowamkwe.lostboyz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LostboyzApplication {

	public static void main(String[] args) {
		System.setProperty("java.runtime.version", "11");
		SpringApplication.run(LostboyzApplication.class, args);
	}

}