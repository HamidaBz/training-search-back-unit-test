package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "projectDescription")
    private String projectDescription;

    @Column(name = "projectLabel")
    private String projectLabel;

    // As project_technologies is an array, it would need a different strategy
    // You might want to have a separate table for project technologies with a foreign key relationship

    @Column(name = "projectStatus")
    private ProjectStatus projectStatus;

    @Column(name = "projectUrl")
    private String projectUrl;

    @Column(name = "projectStartingDate")
    private LocalDateTime projectStartingDate;

    @Column(name = "projectDuration")
    private Integer projectDuration;

    @ManyToOne
    private ApplicantProfile applicantProfile ;



}
