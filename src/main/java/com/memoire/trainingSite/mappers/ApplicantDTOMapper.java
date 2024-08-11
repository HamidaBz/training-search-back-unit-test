package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.models.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ApplicantDTOMapper {
    public ApplicantDTO toDTO(Applicant applicant) {
        return new ApplicantDTO(
                applicant.getUser_id(),
                applicant.getUsername(),
                applicant.getUser_password(),
                applicant.getUser_join_date(),
                applicant.getUser_status(),
                applicant.getUser_phone_number(),
                applicant.getEmail(),
                applicant.getApplicant_firstname(),
                applicant.getApplicant_lastname(),
                applicant.getApplicant_birthday()
        );
    }
    public Applicant toEntity(ApplicantDTO applicantDTO){
        return new Applicant(
                applicantDTO.getUser_id(),
                applicantDTO.getUsername(),
                applicantDTO.getPassword(),
                applicantDTO.getUser_join_date(),
                applicantDTO.getUser_status(),
                applicantDTO.getUser_phone_number(),
                applicantDTO.getEmail(),
                applicantDTO.getApplicant_firstname(),
                applicantDTO.getApplicant_lastname(),
                applicantDTO.getApplicant_birthday());
    }

    public ApplicantResponseDTO toResponseDTO(Applicant applicant){
        return new ApplicantResponseDTO(
                applicant.getUser_id(),
                applicant.getUsername(),
                applicant.getUser_join_date(),
                applicant.getUser_status(),
                applicant.getUser_phone_number(),
                applicant.getEmail(),
                applicant.getApplicant_firstname(),
                applicant.getApplicant_lastname(),
                applicant.getApplicant_birthday(),
                applicant.getApplicantProfile()
        );
    }
}
