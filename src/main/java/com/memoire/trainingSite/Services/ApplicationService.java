package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicationRepo;
import com.memoire.trainingSite.models.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    private ApplicationRepo applicationRepo;

    @Autowired
    public ApplicationService(ApplicationRepo applicationRepo) {
        this.applicationRepo = applicationRepo;
    }
    public Application createApplication(Application application) {
        return applicationRepo.save(application);
    }
    public Optional<Application> getApplicationById(Long applicationId) {
        return applicationRepo.findById(applicationId);
    }
    public List<Application> getAllApplications() {
        return applicationRepo.findAll();
    }
    public Optional<Application> updateApplication(Long applicantId, Application application) {
        Optional<Application> existingApplicantion =  applicationRepo.findById(applicantId);
        if (existingApplicantion.isPresent()) {
            return Optional.of(applicationRepo.save(application));
        }
        return Optional.empty();
    }
    public void deleteApplication(Long applicantionId) {
        applicationRepo.deleteById(applicantionId);
    }
    public List<Application> getApplicationsByApplicantUsername(String applicant_username){
        return applicationRepo.getApplicationsByApplicantUsername(applicant_username);
    }

    public List<Application> getApplicationsByPositionId(Long position_id){
        return applicationRepo.getApplicationsByPositionId(position_id);
    }


}
