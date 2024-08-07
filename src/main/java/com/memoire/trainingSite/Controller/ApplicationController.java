package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicationDTO;
import com.memoire.trainingSite.Services.ApplicationService;
import com.memoire.trainingSite.Services.CompanyProfileService;
import com.memoire.trainingSite.models.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private ApplicationService applicationService  ;
    @Autowired
    public ApplicationController(ApplicationService applicationService) { this.applicationService = applicationService; }

    //function to get all applications
    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getApplicants() {
        List<ApplicationDTO> applicants = applicationService.getAllApplications();
        if (applicants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(applicants, HttpStatus.OK);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDTO> getApplication(@PathVariable Long id) {
        ApplicationDTO application = applicationService.getApplication(id);
        if (application == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(application, HttpStatus.OK);
        }
    }






}
