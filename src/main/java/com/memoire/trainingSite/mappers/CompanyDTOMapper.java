package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.models.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyDTOMapper {
    public CompanyDTO toDTO(Company company) {
        return new CompanyDTO(
                company.getUser_id(),
                company.getUsername(),
                company.getUser_password(),
                company.getCompanyName(),
                company.getUser_join_date(),
                company.getUser_phone_number(),
                company.getNotifications(),
                company.getAlerts(),
                company.getCompanyProfile()
        );
    }
}
