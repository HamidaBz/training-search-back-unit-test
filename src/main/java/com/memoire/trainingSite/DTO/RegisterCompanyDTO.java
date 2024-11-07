package com.memoire.trainingSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCompanyDTO {

        private String username;
        private String password;
        private String phone_number;
        private String email;
        private String role;
        private String companyName;
}
