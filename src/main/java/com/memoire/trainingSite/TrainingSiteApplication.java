package com.memoire.trainingSite;

import com.memoire.trainingSite.DAO.CompanyProfileRepo;
import com.memoire.trainingSite.models.CompanyProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TrainingSiteApplication {

	/*@Autowired
	CompanyProfileRepo companyProfileRepo;*/
	public static void main(String[] args) {
		SpringApplication.run(TrainingSiteApplication.class, args);
	}

/*	@Bean
	CommandLineRunner initial_processing(){

		return (args)-> {
			System.out.println("application is started now");

			CompanyProfile companyprofile = new CompanyProfile();
			companyprofile.setProfile_id(1L);
			companyProfileRepo.save(companyprofile);
		};
	}*/
}

