package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long region_id ;
    private String region_name  ;
    @ManyToMany
    private List<TrainingPosition> trainingPositions = new ArrayList<>() ;

    @ManyToMany(mappedBy = "regions")
    private List<Alert> alerts = new ArrayList<>() ;

}
