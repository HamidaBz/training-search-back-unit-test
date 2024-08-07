package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import com.memoire.trainingSite.Services.SiteUserService;
import com.memoire.trainingSite.models.Applicant;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {


        private final ApplicantService applicantService;
        @Autowired
        public ApplicantController(ApplicantService applicantService ) {
            this.applicantService = applicantService;
        }
        @GetMapping
        public ResponseEntity<List<ApplicantDTO>> getApplicants() {
            List<ApplicantDTO> applicants = applicantService.getApplicants();
            if (applicants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(applicants, HttpStatus.OK);
            }

        }

        //return response entity for all the methods


    @PostMapping("/new")
    public ResponseEntity<Applicant> createApplicant(@RequestBody Applicant applicant) {
        Applicant createdApplicant = applicantService.createApplicant(applicant);
        if (createdApplicant == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDTO> getApplicant(@PathVariable Long id) {
        ApplicantDTO applicant = applicantService.getApplicant(id);
        if (applicant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(applicant, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable Long id, @RequestBody Applicant applicant) {
        Applicant updatedApplicant = applicantService.updateApplicant(id, applicant);
        if (updatedApplicant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedApplicant, HttpStatus.OK);
        }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        try {
            applicantService.deleteApplicant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        

        @PostConstruct
        public void init() {
            applicantService.createApplicant(new Applicant());
        }
    }


