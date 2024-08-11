package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApplicantDTO extends UserDTO{
    private String applicant_firstname;
    private String applicant_lastname;
    private LocalDate applicant_birthday;

    public ApplicantDTO(Long user_id, String username, String password, LocalDateTime user_join_date,UserStatus user_status,
                        String user_phone_number, String email,String applicant_firstname, String applicant_lastname,LocalDate applicant_birthday){
        super(user_id, username, password, user_join_date, user_status, user_phone_number, email);
        this.applicant_firstname = applicant_firstname;
        this.applicant_lastname = applicant_lastname;
        this.applicant_birthday = applicant_birthday;
    }
}
