package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepo extends JpaRepository<Administrator , Long> {
    // You can add custom query methods here if needed
}