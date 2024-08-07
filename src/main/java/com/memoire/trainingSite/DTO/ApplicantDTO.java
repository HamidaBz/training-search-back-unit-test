package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ApplicantDTO {
    private Long user_id;
    private String username;
    private String Applicant_firstname;
    private String Applicant_lastname;
    private String user_phone_number;
    private LocalDateTime user_join_date;
    private LocalDate Applicant_birthday;
    private List<Notification> notifications = new ArrayList<>();
    private List<Application>  applications  = new ArrayList<>();
    private List<Alert> alerts = new ArrayList<>();
    private ApplicantProfile applicantProfile;
}
