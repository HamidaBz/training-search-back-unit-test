package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company , Long> {
    Boolean existsByUsername(String username);
    Optional<Company> findByUsername(String username);
}