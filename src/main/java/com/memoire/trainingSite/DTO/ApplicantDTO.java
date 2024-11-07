package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.Application;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ApplicantDTO extends UserDTO{
    private String applicant_firstname;
    private String applicant_lastname;
    private LocalDate applicant_birthday;
    private List<Application> applications;
    private ApplicantProfile applicantProfile;;

    public ApplicantDTO(Long user_id, String username, String password, LocalDateTime user_join_date, UserStatus user_status,
                        String user_phone_number, String email,
                        Role role, String applicant_firstname, String applicant_lastname,
                        LocalDate applicant_birthday, List<Application> applications, ApplicantProfile applicantProfile){
        super(user_id, username, password, user_join_date, user_status, user_phone_number, email, role);
        this.applicant_firstname = applicant_firstname;
        this.applicant_lastname = applicant_lastname;
        this.applicant_birthday = applicant_birthday;
        this.applications = applications;
        this.applicantProfile = applicantProfile;
    }
}
