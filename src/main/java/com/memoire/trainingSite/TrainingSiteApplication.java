package com.memoire.trainingSite;

import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class TrainingSiteApplication {

	@Autowired
	SiteUserRepo userRepo;



	/*@Autowired
	CompanyProfileRepo companyProfileRepo;*/
	public static void main(String[] args) {
		SpringApplication.run(TrainingSiteApplication.class, args);
	}
	@Bean
	CommandLineRunner initial_processing(){

		return (args)-> {

			//Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");

		//	String jwtToken = jwtService.generateToken(new HashMap<>(), user);
		//	System.out.println(" token  : "  +jwtToken);
		};
	}
}

