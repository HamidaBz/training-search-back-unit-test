package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/applicants")
public class ApplicantController {
    private final ApplicantService applicantService;
    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }
    @GetMapping
    public ResponseEntity<List<ApplicantResponseDTO>> getApplicants() {
        List<ApplicantResponseDTO> applicants = applicantService.getApplicants();
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ApplicantResponseDTO> createApplicant(@RequestBody ApplicantDTO applicant) {
        ApplicantResponseDTO createdApplicant = applicantService.createApplicant(applicant);
        return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<ApplicantResponseDTO> getApplicantById(@PathVariable Long applicantId) {
        Optional<ApplicantResponseDTO> applicant = applicantService.getApplicant(applicantId);
        if (applicant.isPresent()) {
            return new ResponseEntity<>(applicant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{applicantId}")
    public ResponseEntity<ApplicantResponseDTO> updateApplicant(@PathVariable Long applicantId, @RequestBody ApplicantDTO applicant) {
        Optional<ApplicantResponseDTO> updatedApplicant = applicantService.updateApplicant(applicantId, applicant);
        if (!updatedApplicant.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedApplicant.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApplicantResponseDTO> getApplicantByUserName(@RequestParam String username){
        Optional<ApplicantResponseDTO>  applicant = applicantService.getApplicantByUsername(username);
        if(applicant.isPresent()){
            return new ResponseEntity<>(applicant.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
            applicantService.deleteApplicant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


