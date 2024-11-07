package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.Services.ApplicantProfileService;
import com.memoire.trainingSite.models.ApplicantProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/applicantprofiles")
public class ApplicantProfileController {
    private ApplicantProfileService applicantProfileService ;
    @Autowired
    public ApplicantProfileController(ApplicantProfileService applicantProfileService) {
        this.applicantProfileService = applicantProfileService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ApplicantProfile> getApplicantProfileByProfileId(@PathVariable Long profileId) {
        Optional<ApplicantProfile> profile = applicantProfileService.getApplicantProfileByProfileId(profileId);
        return profile.map(pf -> new ResponseEntity<>(pf, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<ApplicantProfile> getApplicantProfileByApplicantUsername(@RequestParam String applicantUsername) {
        Optional<ApplicantProfile> profile = applicantProfileService.getApplicantProfileByApplicantUsername(applicantUsername);
        return profile.map(pf -> new ResponseEntity<>(pf, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{profileId}")
    public ResponseEntity<ApplicantProfile> updateProfile(@PathVariable Long profileId, @RequestBody ApplicantProfile applicantProfile) {
        Optional<ApplicantProfile> updatedProfile =  applicantProfileService.updateProfile(profileId, applicantProfile);
        return updatedProfile.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
