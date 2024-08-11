package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.mappers.ApplicantDTOMapper;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.Company;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
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

    public Optional<ApplicantResponseDTO> createApplicant(ApplicantDTO applicantDTO){
        System.out.println(applicantDTO);
        Applicant applicant = applicantDTOMapper.toEntity(applicantDTO);
        System.out.println(applicant);
        if(!applicantRepo.existsByUsername(applicant.getUsername())) {
            return Optional.of(applicantDTOMapper.toResponseDTO(applicantRepo.save(applicant)));
        }else{
            System.out.println("applicant exists");
            return Optional.empty();
        }

    }
    public Optional<ApplicantResponseDTO> getApplicant(Long id) {
        return applicantRepo.findById(id).map(applicantDTOMapper::toResponseDTO);
    }

    public Optional<ApplicantResponseDTO> getApplicantByUsername(String username){
            return applicantRepo.findByUsername(username).map(applicantDTOMapper::toResponseDTO);

    }
    public List<ApplicantResponseDTO> getApplicants() {
        return applicantRepo.findAll()
                .stream()
                .map(applicantDTOMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ApplicantResponseDTO> updateApplicant(Long id, ApplicantDTO updatedApplicant) {
        Optional<Applicant> applicant =  applicantRepo.findById(id);

        if(applicant.isPresent()){
            applicant.get().setApplicant_firstname(updatedApplicant.getApplicant_firstname());
            applicant.get().setApplicant_lastname(updatedApplicant.getApplicant_lastname());
            applicantRepo.save(applicant.get());
        }
        return applicant.map(applicantDTOMapper::toResponseDTO);
    }

    public void deleteApplicant(Long id) {
        applicantRepo.deleteById(id);
    }



}
