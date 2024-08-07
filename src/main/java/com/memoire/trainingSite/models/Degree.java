package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long degree_id  ;
    private String degree_label ;
    private Integer degree_year ;
    @ManyToOne
    private ApplicantProfile applicantProfile ;

}
