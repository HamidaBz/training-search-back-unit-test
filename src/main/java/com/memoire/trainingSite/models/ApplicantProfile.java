package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
public class ApplicantProfile extends  Profile{
    @OneToMany(mappedBy = "applicantProfile")
    private List<Degree> degrees ;//= new ArrayList<>();
    @OneToMany(mappedBy = "applicantProfile")
    private List<Project> projects ;
    private String Current_level;
    @OneToOne(mappedBy = "applicantProfile")
    private Applicant applicant ;

    public ApplicantProfile(Long profile_id, String profile_intro, LocalDateTime profile_last_update_date,List<Degree> degrees, List<Project> projects, String current_level, Applicant applicant) {
        super(profile_id, profile_intro, profile_last_update_date);
        this.degrees = degrees;
        this.projects = projects;
        Current_level = current_level;
        this.applicant = applicant;
    }
}
