package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import com.memoire.trainingSite.models.SiteUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
/*import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/

public interface SiteUserRepo extends JpaRepository< SiteUser , Long> {
    Boolean existsByUsername(String username);
    Optional<SiteUser> findByUsername(String username);


}