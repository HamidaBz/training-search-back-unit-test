package com.memoire.trainingSite.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memoire.trainingSite.models.Profile;

public interface ProfileRepo extends JpaRepository< Profile , Long> {
    // You can add custom query methods here if needed
}