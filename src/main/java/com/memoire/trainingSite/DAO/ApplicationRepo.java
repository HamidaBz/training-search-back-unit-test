package com.memoire.trainingSite.DAO;
import jakarta.persistence.JoinColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import com.memoire.trainingSite.models.Application;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepo extends JpaRepository< Application , Long> {
    @Query("SELECT ap FROM Application ap WHERE ap.id_application.applicant.username = :applicant_username")
    List<Application> getApplicationsByApplicantUsername(String applicant_username);
    @Query("SELECT ap from Application ap WHERE ap.id_application.position.position_id = :position_id")
    List<Application> getApplicationsByPositionId(Long position_id);
}