package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.DTO.CompanyResponseDTO;
import com.memoire.trainingSite.models.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company toEntity(CompanyDTO companyDTO) {
        return new Company(
                companyDTO.getUser_id(),
                companyDTO.getUsername(),
                companyDTO.getPassword(),
                companyDTO.getUser_join_date(),
                companyDTO.getUser_status(),
                companyDTO.getUser_phone_number(),
                companyDTO.getEmail(),
                companyDTO.getRole(),
                companyDTO.getCompanyName(),
                companyDTO.getCompanyProfile(),
                companyDTO.getTrainingPositions()
                );
    }

    public CompanyResponseDTO toResponseDTO(Company company) {
        return new CompanyResponseDTO(
                company.getUser_id(),
                company.getUsername(),
                company.getUser_join_date(),
                company.getUser_status(),
                company.getUser_phone_number(),
                company.getEmail(),
                company.getRole(),
                company.getCompanyName(),
                company.getCompanyProfile(),
                company.getTrainingPositions()
                );
    }
}
