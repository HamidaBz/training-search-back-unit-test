package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DAO.CompanyRepo;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.AuthResponseDTO;
import com.memoire.trainingSite.DTO.LoginDTO;
import com.memoire.trainingSite.DTO.RegisterApplicantDTO;
import com.memoire.trainingSite.DTO.RegisterCompanyDTO;
import com.memoire.trainingSite.models.*;
import com.memoire.trainingSite.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final SiteUserRepo userRepo ;
    private final CompanyRepo companyRepo;
    private final ApplicantRepo applicantRepo;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public String registerCompany (RegisterCompanyDTO registerDTO) {
       Company company =
               new Company(
                       null,
                       registerDTO.getUsername(),
                       passwordEncoder.encode(registerDTO.getPassword()),
                       LocalDateTime.now(),
                       UserStatus.ACTIVE,
                       registerDTO.getPhone_number(),
                       registerDTO.getEmail(),
                       Role.COMPANY,
                       registerDTO.getCompanyName(),
                       new CompanyProfile(),
                       List.of());
        companyRepo.save(company);
        return authenticate(new LoginDTO( registerDTO.getUsername(),registerDTO.getPassword()));

    }

    public String registerApplicant (RegisterApplicantDTO registerDTO) {
        Applicant applicant =
                new Applicant(
                        null,
                        registerDTO.getUsername(),
                        passwordEncoder.encode(registerDTO.getPassword()),
                        LocalDateTime.now(),
                        UserStatus.ACTIVE,
                        registerDTO.getPhone_number(),
                        registerDTO.getEmail(),
                        Role.APPLICANT,
                        registerDTO.getFirstname(),
                        registerDTO.getLastname(),
                        registerDTO.getBirthday(),
                        List.of(),
                        new ApplicantProfile());
        applicantRepo.save(applicant);
        return authenticate(new LoginDTO( registerDTO.getUsername(),registerDTO.getPassword()));

    }

    public String authenticate(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()));

        //this is so the user doesnt have to log in every time
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<SiteUser> userOpt = userRepo.findByUsername(loginDTO.getUsername());
        String token = null;
        if(userOpt.isPresent()) {
            token = jwtService.generateToken(new HashMap<>(), userOpt.get());
        }
        return token;
    }
}
