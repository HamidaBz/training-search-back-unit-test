package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CompanyResponseDTO extends UserResponseDTO{
    private  String companyName;

    public CompanyResponseDTO(Long user_id, String username, LocalDateTime user_join_date, UserStatus user_status,
                              String user_phone_number, String email, String companyName){
        super(user_id, username, user_join_date, user_status, user_phone_number, email);
        this.companyName = companyName;
    }

}
