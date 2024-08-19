package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.mappers.ApplicantDTOMapper;
import com.memoire.trainingSite.models.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ApplicantResponseDTO createApplicant(ApplicantDTO applicantDTO){
        Applicant applicant = applicantDTOMapper.toEntity(applicantDTO);
        return applicantDTOMapper.toResponseDTO(applicantRepo.save(applicant));

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

    public Optional<ApplicantResponseDTO> updateApplicant(Long id, ApplicantDTO updatedApplicantDTO) {
        Optional<Applicant> applicant =  applicantRepo.findById(id);

        if(applicant.isPresent()){
            Applicant  updatedApplicant = applicantDTOMapper.toEntity(updatedApplicantDTO);
            applicantRepo.save(updatedApplicant);
        }
        return applicant.map(applicantDTOMapper::toResponseDTO);
    }

    public void deleteApplicant(Long id) {
        applicantRepo.deleteById(id);
    }



}
