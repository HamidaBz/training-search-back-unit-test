package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.RegisterDTO;
import com.memoire.trainingSite.models.SiteUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private SiteUserRepo userRepo ;

    AuthenticationService(SiteUserRepo userRepo){
        this.userRepo = userRepo;
    }

    public boolean authenticate(RegisterDTO authrequest) {
        System.out.println("used the authentication contoller");


        String um = authrequest.getUsername() ;

        Optional<SiteUser> user = userRepo.findByUsername(um);
        if (user.isEmpty()) {
            System.out.println("user is null");
        }

        if (user.get().getUsername().equals("aymene22k@gmail.com")) {
            return true; // Authentication successful
        } else {
            return false; // Authentication failed
        }
    }
}
