package com.memoire.trainingSite.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memoire.trainingSite.models.Region;

public interface RegionRepo extends JpaRepository< Region , Long> {
    // You can add custom query methods here if needed
}