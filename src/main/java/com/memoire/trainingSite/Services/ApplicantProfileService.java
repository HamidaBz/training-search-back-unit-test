package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantProfileRepo;
import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DAO.ProfileRepo;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantProfileService {
    private ApplicantProfileRepo applicantProfileRepo ;
    @Autowired
    public ApplicantProfileService(ApplicantProfileRepo applicantProfileRepo) { this.applicantProfileRepo = applicantProfileRepo; }
    public ApplicantProfile updateLevel(Long id, ApplicantProfile applicantProfile) {
        if (applicantProfileRepo.existsById(id)) {
            return applicantProfileRepo.save(applicantProfile);
        } else {
            throw new IllegalArgumentException( id + " does not exist");
        }
    }

    public ApplicantProfile getApplicantProfile(Long id) {
        return (ApplicantProfile) applicantProfileRepo.findById(id).orElseThrow(() -> new IllegalArgumentException( id + " does not exist"));
    }

    public Profile updateProfile(Long id, ApplicantProfile applicantProfile) {
        if(applicantProfileRepo.existsById(id)){
            return applicantProfileRepo.save(applicantProfile);
}else{
            throw new IllegalArgumentException( id + " does not exist");
        }

    }
}
