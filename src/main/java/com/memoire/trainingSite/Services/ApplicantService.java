package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.mappers.ApplicantMapper;
import com.memoire.trainingSite.models.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    private ApplicantRepo applicantRepo  ;
    private ApplicantMapper applicantMapper;
    @Autowired
    public ApplicantService(ApplicantRepo applicantRepo, ApplicantMapper applicantMapper){
        this.applicantRepo = applicantRepo;
        this.applicantMapper = applicantMapper;
    }

    public ApplicantResponseDTO createApplicant(ApplicantDTO applicantDTO){
        Applicant applicant = applicantMapper.toEntity(applicantDTO);
        return applicantMapper.toResponseDTO(applicantRepo.save(applicant));

    }
    public Optional<ApplicantResponseDTO> getApplicantById(Long applicant_id) {
        return applicantRepo.findById(applicant_id).map(applicantMapper::toResponseDTO);
    }

    public Optional<ApplicantResponseDTO> getApplicantByUsername(String username){
            return applicantRepo.findByUsername(username).map(applicantMapper::toResponseDTO);

    }
    public List<ApplicantResponseDTO> getApplicants() {
        return applicantRepo.findAll()
                .stream()
                .map(applicantMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ApplicantResponseDTO> updateApplicant(Long applicant_id, ApplicantDTO updatedApplicantDTO) {
        Optional<Applicant> applicant =  applicantRepo.findById(applicant_id);

        if(applicant.isPresent()){
            Applicant  updatedApplicant = applicantMapper.toEntity(updatedApplicantDTO);
            applicantRepo.save(updatedApplicant);
            return Optional.of(applicantMapper.toResponseDTO(updatedApplicant));
        }
        return Optional.empty();
    }

    public void deleteApplicant(Long applicant_id) {
        applicantRepo.deleteById(applicant_id);
    }

}
