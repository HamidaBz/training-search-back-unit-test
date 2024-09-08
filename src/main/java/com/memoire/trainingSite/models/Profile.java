package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Profile {
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    @Column(name="profileId")
    private Long Profile_id;
    @Column(name="Intro")
    private String Profile_intro;
    @Column(name="UpdateDate")
    private LocalDateTime Profile_last_update_date;

}
