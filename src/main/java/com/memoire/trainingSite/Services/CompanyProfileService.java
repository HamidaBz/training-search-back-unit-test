package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.CompanyProfileRepo;
import com.memoire.trainingSite.models.CompanyProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyProfileService {
    private CompanyProfileRepo companyProfileRepo ;
    @Autowired
    public CompanyProfileService(CompanyProfileRepo companyProfileRepo) {
        this.companyProfileRepo = companyProfileRepo;
    }
    public Optional<CompanyProfile> getCompanyProfileByProfileId(Long profileId) {
        Optional<CompanyProfile> profile = companyProfileRepo.findById(profileId);
        return profile;
    }
    public Optional<CompanyProfile> getCompanyProfileByCompanyUsername(String username) {
        Optional<CompanyProfile> companyProfile = companyProfileRepo.findByCompanyUsername(username);
        return companyProfile;
    }
    public Optional<CompanyProfile> updateCompanyProfile(Long profileId, CompanyProfile newCompanyProfile) {
        Optional<CompanyProfile> existingCompanyProfile = companyProfileRepo.findById(profileId);
        if(existingCompanyProfile.isPresent()){
            return Optional.of(companyProfileRepo.save(newCompanyProfile));
        }
        return Optional.empty();
    }
}
