package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
public class Company extends SiteUser {
    @Column(name = "CompanyName", nullable = false)
    private  String companyName;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_profile_fk")
    private CompanyProfile companyProfile = new CompanyProfile();
    @OneToMany(mappedBy = "company")
    private List<TrainingPosition> trainingPositions = new ArrayList<>() ;

    public Company(Long user_id, String username, String password, LocalDateTime user_join_date, UserStatus user_status,
                   String user_phone_number, String email, List<Role> roles, String companyName, CompanyProfile companyProfile,
                   List<TrainingPosition> trainingPositions){
        super(user_id,  username, password, user_join_date, user_status, user_phone_number, email, roles);
        this.companyName = companyName;
        this.companyProfile = companyProfile;
        this.trainingPositions = trainingPositions;
    }

}
