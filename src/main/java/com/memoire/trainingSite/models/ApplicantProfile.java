package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantProfile extends  Profile{

    @OneToMany(mappedBy = "applicantProfile")
    private List<Degree> degrees ;
    @OneToMany(mappedBy = "applicantProfile")
    private List<Project> projects ;
    private String Current_level;
    @OneToOne(mappedBy = "applicantProfile")
    private Applicant applicant ;

}
