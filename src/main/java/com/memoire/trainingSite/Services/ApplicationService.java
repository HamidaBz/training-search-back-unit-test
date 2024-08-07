package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicationRepo;
import com.memoire.trainingSite.DTO.ApplicationDTO;
import com.memoire.trainingSite.mappers.ApplicationDTOMapper;
import com.memoire.trainingSite.models.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private ApplicationRepo applicationRepo;
    private ApplicationDTOMapper applicationDTOMapper;
    @Autowired
    public ApplicationService(ApplicationRepo applicationRepo , ApplicationDTOMapper applicationDTOMapper){
        this.applicationRepo = applicationRepo;
        this.applicationDTOMapper = applicationDTOMapper;
    }

    public void deleteApplication(Long id) {
        applicationRepo.deleteById(id);
    }
    //create application it returns an application object and takes in an application object as a parameter
    public Application createApplication(Application application){
        try {
            return applicationRepo.save(application);
        }catch(Exception e) {
            e.printStackTrace();
            System.err.println();
            return null ;
        }
    }
    //modify application it returns an application object and takes in a long id and an application object as parameters

    public Application updateApplication(Long id, Application application) {
        if (applicationRepo.existsById(id)) {
            return applicationRepo.save(application);
        } else {
            throw new IllegalArgumentException( id + " does not exist");
        }
    }
    //function to return all applications
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepo.findAll().stream().map(applicationDTOMapper::toDTO).collect(Collectors.toList());
    }
    public ApplicationDTO getApplication(Long id) {
        return applicationRepo.findById(id).map(applicationDTOMapper::toDTO).orElseThrow(() -> new IllegalArgumentException( id + " does not exist"));
    }



}
