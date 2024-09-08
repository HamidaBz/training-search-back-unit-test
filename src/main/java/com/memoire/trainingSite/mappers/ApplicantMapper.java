package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.models.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {
    public Applicant toEntity(ApplicantDTO applicantDTO){
        return new Applicant(
                applicantDTO.getUser_id(),
                applicantDTO.getUsername(),
                applicantDTO.getPassword(),
                applicantDTO.getUser_join_date(),
                applicantDTO.getUser_status(),
                applicantDTO.getUser_phone_number(),
                applicantDTO.getEmail(),
                applicantDTO.getRoles(),
                applicantDTO.getApplicant_firstname(),
                applicantDTO.getApplicant_lastname(),
                applicantDTO.getApplicant_birthday(),
                applicantDTO.getApplications(),
                applicantDTO.getApplicantProfile()
        );
    }

    public ApplicantResponseDTO toResponseDTO(Applicant applicant){
        return new ApplicantResponseDTO(
                applicant.getUser_id(),
                applicant.getUsername(),
                applicant.getUser_join_date(),
                applicant.getUser_status(),
                applicant.getUser_phone_number(),
                applicant.getEmail(),
                applicant.getRoles(),
                applicant.getApplicant_firstname(),
                applicant.getApplicant_lastname(),
                applicant.getApplicant_birthday(),
                applicant.getApplications(),
                applicant.getApplicantProfile()
        );
    }
}
