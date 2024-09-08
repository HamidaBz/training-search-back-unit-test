package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepo extends  JpaRepository<Applicant , Long> {

    Boolean existsByUsername(String username);
    Optional<Applicant> findByUsername(String username);
}