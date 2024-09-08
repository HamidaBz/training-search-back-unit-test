package com.memoire.trainingSite.Controller;
/*
import com.memoire.trainingSite.DAO.RoleRepo;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.AuthResponseDTO;
import com.memoire.trainingSite.DTO.LoginDTO;
import com.memoire.trainingSite.DTO.RegisterDTO;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.UserStatus;
import com.memoire.trainingSite.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.memoire.trainingSite.Services.AuthenticationService ;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/authenticate")
public class AuthController {


    private SiteUserRepo userRepo ;
    private RoleRepo roleRepo ;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager, RoleRepo roleRepo, SiteUserRepo userRepo , JWTGenerator jwtGenerator){
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager ;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.jwtGenerator = jwtGenerator;

    }
    //login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()));
        //this is so the user doesnt have to log in every time
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token) ,  HttpStatus.OK);
    }





    //register endpoint
    @PostMapping("/register")
    public ResponseEntity<String> authenticate(@RequestBody RegisterDTO registerDTO) {
        System.out.println(registerDTO);
        SiteUser user = new SiteUser();
        user.setUsername( registerDTO.getUsername());
        user.setUser_password(passwordEncoder.encode(registerDTO.getPassword()));
        user.setUser_join_date(LocalDateTime.now());
        user.setUser_status(UserStatus.ACTIVE);
        user.setUser_phone_number("123-456-789" );
        user.setNotifications(new ArrayList<>());
        user.setAlerts(new ArrayList<>());

        userRepo.save(user) ;
        return new ResponseEntity<>("user registered successfully", HttpStatus.OK);

    }

}
*/