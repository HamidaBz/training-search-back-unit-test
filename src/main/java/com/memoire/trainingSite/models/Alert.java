package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alert_id ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser siteUser ;

    @ManyToMany
    @JoinTable(name = "alert_keyword",
            joinColumns = @JoinColumn(name = "alert_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private List<Keyword> keywords = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "alert_region" ,
            joinColumns = @JoinColumn(name = "alert_id") ,
            //foreign key
            inverseJoinColumns = @JoinColumn(name = "region_id")
    )
    private List<Region> regions = new ArrayList<>() ;


}
