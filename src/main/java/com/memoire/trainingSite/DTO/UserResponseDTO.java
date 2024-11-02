package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long user_id;
    private String username;
    private LocalDateTime user_join_date;
    private UserStatus user_status;
    private String user_phone_number;
    private String email;
    private Role role;

}
