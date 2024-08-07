package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepo extends  JpaRepository<Applicant , Long> {
    // You can add custom query methods here if needed
}