package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TrainingPositionDTO {

    private Long  position_id ;
    private String  position_description;
    private LocalDateTime position_posting_date;
    private Boolean  position_visibility;
    private String position_status;
    private Integer  position_free_places;
    private PositionWorkType position_work_type;
    private LocalDateTime position_starting_date;
    private Integer position_duration;
    private String position_requirements;
    private String position_image ;
    private List<String> regions = new ArrayList<>() ;
    private List<Notification> notifications = new ArrayList<>() ;
    private String company;
}
