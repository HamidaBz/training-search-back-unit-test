package com.memoire.trainingSite.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class CompanyProfile extends Profile{
    @Column(name = "StaffCount")
    private String company_nbr_employees;
    @OneToOne(mappedBy = "companyProfile")
    private Company company;

    public CompanyProfile(Long profile_id, String profile_intro, LocalDateTime profile_last_update_date, String company_nbr_employees, Company company) {
        super(profile_id, profile_intro, profile_last_update_date);
        this.company_nbr_employees = company_nbr_employees;
        this.company = company;
    }
}
