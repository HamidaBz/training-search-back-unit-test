package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DAO.CompanyRepo;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.mappers.ApplicantDTOMapper;
import com.memoire.trainingSite.mappers.CompanyDTOMapper;
import com.memoire.trainingSite.mappers.SiteUserDTOMapper;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.Company;
import com.memoire.trainingSite.models.SiteUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteUserService {
    private SiteUserRepo siteUserRepo ;
    @Autowired
    public SiteUserService(SiteUserRepo siteUserRepo){
        this.siteUserRepo = siteUserRepo ;
    }

    public SiteUser createUser(SiteUser siteUser) {
        return siteUserRepo.save(siteUser);
    }


    public List<SiteUser> getAllUsers() {
        return siteUserRepo.findAll() ;
    }

    public Optional<SiteUser> getUserById(Long id) {
        return siteUserRepo.findById(id) ;
    }



    public SiteUser updateUser(Long id, SiteUser siteUser) {

        if (siteUserRepo.existsById(id)) {
            siteUser.setUser_id(id);
            return siteUserRepo.save(siteUser);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
    }

    public void deleteUser(Long id) {

        if (siteUserRepo.existsById(id)) {
            siteUserRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
    }
}
