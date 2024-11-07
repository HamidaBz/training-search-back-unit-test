package com.memoire.trainingSite.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memoire.trainingSite.models.TrainingPosition;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingPositionRepo extends JpaRepository< TrainingPosition , Long> {
    @Query("SELECT tp from TrainingPosition  tp WHERE tp.company.user_id = : companyId")
    List<TrainingPosition> getPositionsByCompanyId(Long companyId);

}