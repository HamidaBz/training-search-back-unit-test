package com.memoire.trainingSite.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.memoire.trainingSite.models.SiteUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SiteUserRepo extends JpaRepository< SiteUser , Long> {

    @Query("SELECT u FROM SiteUser u WHERE u.username = :username")
    SiteUser findByUsername(@Param("username") String username)  ;

    boolean existsByUsername(String username);


}