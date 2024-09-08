package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.CompanyProfileRepo;
import com.memoire.trainingSite.DAO.ProfileRepo;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.CompanyProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfileService {
    private CompanyProfileRepo companyProfileRepo ;
    @Autowired
    public CompanyProfileService(CompanyProfileRepo companyProfileRepo) { this.companyProfileRepo = companyProfileRepo; }
    public CompanyProfile UpdateCompanyProfile(Long id, CompanyProfile companyProfile) {
        if (companyProfileRepo.existsById(id)) {
            return companyProfileRepo.save(companyProfile);
        } else {
            throw new IllegalArgumentException( id + " does not exist");
        }
    }

    public CompanyProfile getCompanyProfile(Long id) {
        return (CompanyProfile) companyProfileRepo.findById(id).orElseThrow(() -> new IllegalArgumentException( id + " does not exist"));

    }
}
