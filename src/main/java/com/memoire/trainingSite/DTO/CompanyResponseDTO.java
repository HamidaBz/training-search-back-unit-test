package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.CompanyProfile;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.TrainingPosition;
import com.memoire.trainingSite.models.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CompanyResponseDTO extends UserResponseDTO{
    private  String companyName;
    private CompanyProfile companyProfile;
    private List<TrainingPosition> trainingPositions;

    public CompanyResponseDTO(Long user_id, String username, LocalDateTime user_join_date, UserStatus user_status,
                              String user_phone_number, String email, Role role,  String companyName,
                              CompanyProfile companyProfile, List<TrainingPosition> trainingPositions){
        super(user_id, username, user_join_date, user_status, user_phone_number, email, role);
        this.companyName = companyName;
        this.companyProfile = companyProfile;
        this.trainingPositions = trainingPositions;

    }

}
