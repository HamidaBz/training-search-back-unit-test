package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.UserStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long user_id;
    private String username;
    private String password;
    private LocalDateTime user_join_date;
    private UserStatus user_status;
    private String user_phone_number;
    private String email;
}
