package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantProfileRepo;
import com.memoire.trainingSite.models.ApplicantProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicantProfileService {
    private ApplicantProfileRepo applicantProfileRepo ;
    @Autowired
    public ApplicantProfileService(ApplicantProfileRepo applicantProfileRepo) {
        this.applicantProfileRepo = applicantProfileRepo;
    }
    public Optional<ApplicantProfile> getApplicantProfileByProfileId(Long id) {
        Optional<ApplicantProfile> applicantProfile = applicantProfileRepo.findById(id);
        return applicantProfile;
    }
    public Optional<ApplicantProfile> getApplicantProfileByApplicantUsername(String username) {
        Optional<ApplicantProfile> applicantProfile = applicantProfileRepo.findByApplicantUsername(username);
        return applicantProfile;
    }

    public Optional<ApplicantProfile> updateProfile(Long id, ApplicantProfile applicantProfile) {
        Optional<ApplicantProfile> existingApplicantProfile =  applicantProfileRepo.findById(id);
        if(existingApplicantProfile.isPresent()){
            return Optional.of(applicantProfileRepo.save(applicantProfile));
        }
        return Optional.empty();
    }
}
