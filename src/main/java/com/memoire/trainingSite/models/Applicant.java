package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name ="Applicant")
public class Applicant extends SiteUser {
    @Column(name="FirstName")
    private String Applicant_firstname;

    @Column(name="LastName")
    private String Applicant_lastname;

    @Column(name="Birthday")
    private LocalDate Applicant_birthday;



    @OneToMany(mappedBy = "id_application.applicant")
    private List<Application> applications = new ArrayList<>() ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_profile_fk")
    private ApplicantProfile applicantProfile ;

}
