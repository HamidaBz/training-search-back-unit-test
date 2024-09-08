package com.memoire.trainingSite.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.memoire.trainingSite.models.Application;

public interface ApplicationRepo extends JpaRepository< Application , Long> {
    // You can add custom query methods here if needed
}