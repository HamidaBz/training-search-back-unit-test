package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class ApplicantResponseDTO extends UserResponseDTO{
    private String applicant_firstname;
    private String applicant_lastname;
    private LocalDate applicant_birthday;
    private ApplicantProfile applicantProfile;

    public ApplicantResponseDTO(Long user_id, String username, LocalDateTime user_join_date,UserStatus user_status,
                        String user_phone_number, String email,String applicant_firstname, String applicant_lastname,LocalDate applicant_birthday,
                        ApplicantProfile applicantProfile){
        super(user_id, username, user_join_date, user_status, user_phone_number, email);
        this.applicant_firstname = applicant_firstname;
        this.applicant_lastname = applicant_lastname;
        this.applicant_birthday = applicant_birthday;
        this.applicantProfile = applicantProfile;

    }
}

