package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class CompanyDTO extends UserDTO{

    private  String companyName;

    public CompanyDTO(Long user_id, String username, String password, LocalDateTime user_join_date, UserStatus user_status,
                      String user_phone_number, String email, List<Role> roles, String companyName){
        super(user_id, username, password, user_join_date, user_status, user_phone_number, email, roles);
        this.companyName = companyName;
    }
}
