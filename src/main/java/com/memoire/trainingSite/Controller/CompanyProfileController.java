package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.Services.CompanyProfileService;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.CompanyProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/companyprofiles")
public class CompanyProfileController {
    private CompanyProfileService companyProfileService;

    @Autowired
    public CompanyProfileController(CompanyProfileService companyProfileService) {
        this.companyProfileService = companyProfileService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<CompanyProfile> getCompanyProfileByProfileId(@PathVariable Long profileId) {
        Optional<CompanyProfile> profile = companyProfileService.getCompanyProfileByProfileId(profileId);
        return profile.map(pf -> new ResponseEntity<>(pf, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<CompanyProfile> getCompanyProfileByApplicantUsername(@RequestParam String companyUsername) {
        Optional<CompanyProfile> profile = companyProfileService.getApplicantProfileByCompanyUsername(companyUsername);
        return profile.map(pf -> new ResponseEntity<>(pf, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{profileId}")
    public ResponseEntity<CompanyProfile> updateProfile(
            @PathVariable Long profileId, @RequestBody CompanyProfile companyProfile) {
        Optional<CompanyProfile> updatedProfile =  companyProfileService.UpdateCompanyProfile(profileId, companyProfile);
        return updatedProfile.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
