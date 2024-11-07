package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name ="Applicant")
public class Applicant extends SiteUser {
    @Column(name="FirstName", nullable=false)
    private String applicant_firstname;

    @Column(name="LastName", nullable = false)
    private String applicant_lastname;

    @Column(name="Birthday", nullable = false)
    private LocalDate applicant_birthday;

    @OneToMany(mappedBy = "id_application.applicant", fetch = FetchType.LAZY)
    private List<Application> applications;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "applicant_profile_fk")
    private ApplicantProfile applicantProfile ;

    public Applicant(Long user_id, String username, String password, LocalDateTime user_join_date,UserStatus user_status,
                     String user_phone_number, String email, Role role,
                     String applicant_firstname, String applicant_lastname,LocalDate applicant_birthday,
                     List<Application> applications, ApplicantProfile applicantProfile){
        super(user_id,  username, password, user_join_date, user_status, user_phone_number, email, role);
        this.applicant_firstname = applicant_firstname;
        this.applicant_lastname = applicant_lastname;
        this.applicant_birthday = applicant_birthday;
        this.applications = applications;
        this.applicantProfile = applicantProfile;
    }



}
