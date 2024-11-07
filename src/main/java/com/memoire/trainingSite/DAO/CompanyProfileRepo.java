package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyProfileRepo extends JpaRepository<CompanyProfile, Long> {
    @Query("SELECT cp from CompanyProfile  cp  WHERE cp.company.username = :companyUsername")
    Optional<CompanyProfile> findByCompanyUsername(String companyUsername);
}