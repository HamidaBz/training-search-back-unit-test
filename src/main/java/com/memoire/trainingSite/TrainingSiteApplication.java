package com.memoire.trainingSite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TrainingSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingSiteApplication.class, args);
	}

	@Bean
	CommandLineRunner initial_processing(){
		return (args)-> System.out.println("application is started now");
	}
}

