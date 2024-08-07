package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.Services.ApplicantProfileService;
import com.memoire.trainingSite.Services.CompanyProfileService;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.CompanyProfile;
import com.memoire.trainingSite.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/companyprofile")
    public class CompanyProfileController {
        private CompanyProfileService companyProfileService  ;
        @Autowired
        public CompanyProfileController(CompanyProfileService companyProfileService) { this.companyProfileService = companyProfileService; }
        @PutMapping("/{id}")
        public CompanyProfile upadateCompanyProfile(@PathVariable Long id, @RequestBody CompanyProfile companyProfile) {
            return companyProfileService.UpdateCompanyProfile(id, companyProfile);
        }
        //create the @getMapping for the company profile
        @GetMapping("/{id}")
        public CompanyProfile getCompanyProfile(@PathVariable Long id) {
            return companyProfileService.getCompanyProfile(id);
        }
}
