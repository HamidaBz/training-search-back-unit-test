package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "positionId")
    private Long  position_id ;
    @Column(name = "PositionDescription")
    private String  position_description;
    @Column(name = "PositionPostingDate")
    private LocalDateTime  position_posting_date;
    @Column(name = "PositionVisibility")
    private Boolean  position_visibility;
    @Column(name = "PositionStatus")
    private PositionStatus  position_status;
    @Column(name = "PositionFreePlaces")
    private Integer  position_free_places;
    @Column(name = "PositionWorkType")
    private PositionWorkType  position_work_type;
    @Column(name = "PositionStartingDate")
    private LocalDateTime position_starting_date;
    @Column(name = "PositionDuration")
    private Integer position_duration;
    @Column(name = "PositionRequirements")
    private String position_requirements;
    @ManyToMany(mappedBy = "trainingPositions")
    private List<Region> regions = new ArrayList<>() ;
    @OneToMany(mappedBy = "id_application.position")
    private List<Application> applications = new ArrayList<>() ;

    @OneToMany(mappedBy = "id_notification.position")
    private List<Notification> notifications = new ArrayList<>() ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_fk")
    private Company company;

    @Column(name = "position_image")
    private String position_image ;
}
