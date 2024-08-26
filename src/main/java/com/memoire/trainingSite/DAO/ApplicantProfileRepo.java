package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.ApplicantProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicantProfileRepo extends JpaRepository<ApplicantProfile, Long> {
    @Query("SELECT ap FROM ApplicantProfile ap WHERE  ap.applicant.username = :applicantUsername")
    Optional<ApplicantProfile> findByApplicantUsername(String applicantUsername);
}