package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.*;
import jakarta.persistence.OneToMany;
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
    private CompanyProfile companyProfile;
    private List<TrainingPosition> trainingPositions;

    public CompanyDTO(Long user_id, String username, String password, LocalDateTime user_join_date, UserStatus user_status,
                      String user_phone_number, String email, Role role, String companyName,
                      CompanyProfile companyProfile, List<TrainingPosition> trainingPositions){
        super(user_id, username, password, user_join_date, user_status, user_phone_number, email, role);
        this.companyName = companyName;
        this.companyProfile = companyProfile;
        this.trainingPositions = trainingPositions;
    }
}
