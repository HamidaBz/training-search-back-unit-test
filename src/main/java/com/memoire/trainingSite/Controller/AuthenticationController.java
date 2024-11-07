package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.AuthResponseDTO;
import com.memoire.trainingSite.DTO.LoginDTO;
import com.memoire.trainingSite.DTO.RegisterCompanyDTO;
import com.memoire.trainingSite.DTO.RegisterApplicantDTO;
import com.memoire.trainingSite.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {

        String token = authenticationService.authenticate(loginDTO);

        if(token != null) {
            return new ResponseEntity<>(new AuthResponseDTO(token) ,  HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





    //register endpoint
    @PostMapping("/register-company")
    public ResponseEntity<AuthResponseDTO> registerCompany(@RequestBody RegisterCompanyDTO registerDTO) {

        String token = authenticationService.registerCompany(registerDTO);

        if(token == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }else{
            if(token.equals("username exists")) {
                return new ResponseEntity<>(new AuthResponseDTO(token) ,  HttpStatus.FOUND);
            }else{
                return new ResponseEntity<>(new AuthResponseDTO(token) ,  HttpStatus.OK);
            }
        }
    }

    @PostMapping("/register-applicant")
    public ResponseEntity<AuthResponseDTO> registerApplicant(@RequestBody RegisterApplicantDTO registerDTO) {

        String token = authenticationService.registerApplicant(registerDTO);

        if(token == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }else{
            if(token.equals("username exists")) {
                return new ResponseEntity<>(new AuthResponseDTO(token) ,  HttpStatus.FOUND);
            }else{
                return new ResponseEntity<>(new AuthResponseDTO(token) ,  HttpStatus.OK);
            }
        }
    }
}
