package com.memoire.trainingSite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company extends SiteUser {
    @Column(name = "CompanyName")
    private  String CompanyName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_profile_fk")
    private CompanyProfile companyProfile ;


    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<TrainingPosition> trainingPositions = new ArrayList<>() ;

}
