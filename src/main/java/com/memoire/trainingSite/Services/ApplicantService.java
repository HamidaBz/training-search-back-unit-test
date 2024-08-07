package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.mappers.ApplicantDTOMapper;
import com.memoire.trainingSite.models.Applicant;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    private ApplicantRepo applicantRepo  ;
    private ApplicantDTOMapper applicantDTOMapper;
    @Autowired
    public ApplicantService(ApplicantRepo applicantRepo ,  ApplicantDTOMapper applicantDTOMapper){
        this.applicantRepo = applicantRepo;
        this.applicantDTOMapper = applicantDTOMapper;
    }

    public Applicant createApplicant(Applicant applicant){
        try {
            return applicantRepo.save(applicant);
        }catch(Exception e) {
            e.printStackTrace();
            System.err.println();
            return null ;
        }
    }
    public ApplicantDTO getApplicant(Long id) {
        return applicantRepo.findById(id).map(applicantDTOMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Applicant not found with id: " + id));
    }
    public List<ApplicantDTO> getApplicants() {
        return applicantRepo.findAll()
                .stream()
                .map(applicant -> applicantDTOMapper.toDTO(applicant))
                .collect(Collectors.toList());
    }

    public Applicant updateApplicant(Long id, Applicant updatedApplicant) {

        Applicant applicant =  applicantRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Applicant not found with id: " + id));
        applicant.setApplicant_firstname(updatedApplicant.getApplicant_firstname());
        applicant.setApplicant_lastname(updatedApplicant.getApplicant_lastname());
        return applicantRepo.save(applicant);
    }

    public void deleteApplicant(Long id) {
        applicantRepo.deleteById(id);
    }



}
