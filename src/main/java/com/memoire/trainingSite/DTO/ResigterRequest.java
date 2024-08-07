package com.memoire.trainingSite.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResigterRequest {

        private String username;
        private String password;
        private String email;
        private String role;
}
