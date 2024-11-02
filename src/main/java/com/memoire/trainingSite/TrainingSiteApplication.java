package com.memoire.trainingSite;

import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.UserStatus;
import com.memoire.trainingSite.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class TrainingSiteApplication {
	@Autowired
	JWTService jwtService;
	@Autowired
	SiteUserRepo userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	/*@Autowired
	CompanyProfileRepo companyProfileRepo;*/
	public static void main(String[] args) {
		SpringApplication.run(TrainingSiteApplication.class, args);
	}
	@Bean
	CommandLineRunner initial_processing(){
		SiteUser user =  new SiteUser(1L,"username", passwordEncoder.encode("password"), LocalDateTime.now(), UserStatus.ACTIVE,
				"0000000", "jhhhh@gmail.com", List.of(new Role(1,"ADMIN")));
		userRepo.save(user);
		return (args)-> {

			//Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");

		//	String jwtToken = jwtService.generateToken(new HashMap<>(), user);
		//	System.out.println(" token  : "  +jwtToken);
		};
	}
}

