package com.memoire.trainingSite.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterApplicantDTO {
    private String username;
    private String password;
    private String phone_number;
    private String email;
    private String role;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
}
