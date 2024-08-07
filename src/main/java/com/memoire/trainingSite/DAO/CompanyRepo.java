package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company , Long> {
    // You can add custom query methods here if needed
}