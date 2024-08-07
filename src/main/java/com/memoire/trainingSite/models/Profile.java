package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Profile {
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    @Column(name="profileId")
    private long Profile_id;
    @Column(name="Intro")
    private long Profile_intro;
    @Column(name="UpdateDate")
    private LocalDateTime Profile_last_update_date;

}
