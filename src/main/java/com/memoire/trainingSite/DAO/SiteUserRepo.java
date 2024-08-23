package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteUserRepo extends JpaRepository< SiteUser , Long> {
    Boolean existsByUsername(String username);
    Optional<SiteUser> findByUsername(String username);


}