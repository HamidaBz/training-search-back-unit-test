package com.memoire.trainingSite.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.memoire.trainingSite.models.Degree;

public interface DegreeRepo extends JpaRepository< Degree , Long> {
}