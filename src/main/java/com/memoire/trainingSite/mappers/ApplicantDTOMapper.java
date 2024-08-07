package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.models.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ApplicantDTOMapper {
    public ApplicantDTO toDTO(Applicant applicant) {
        Applicant applicant1 = new Applicant() ;

        return new ApplicantDTO(
                applicant.getUser_id(),
                applicant.getUsername(),
                applicant.getApplicant_firstname(),
                applicant.getApplicant_lastname(),
                applicant.getUser_phone_number(),
                applicant.getUser_join_date(),
                applicant.getApplicant_birthday(),
                applicant.getNotifications(),
                applicant.getApplications(),
                applicant.getAlerts(),
                applicant.getApplicantProfile()
        );
    }
}
