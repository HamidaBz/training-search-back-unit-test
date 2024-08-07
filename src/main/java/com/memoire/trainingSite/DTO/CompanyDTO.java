package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.Alert;
import com.memoire.trainingSite.models.CompanyProfile;
import com.memoire.trainingSite.models.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class CompanyDTO {

    private Long user_id;
    private String username;
    private String user_password;
    private  String CompanyName;
    private LocalDateTime user_join_date;
    private String user_phone_number;
    private List<Notification> notifications = new ArrayList<>();
    private List<Alert> alerts = new ArrayList<>() ;
    private CompanyProfile companyProfile ;
}
