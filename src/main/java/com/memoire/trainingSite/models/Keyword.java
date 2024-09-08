package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyword_id ;
    private String keyword_value ;

    @ManyToMany(mappedBy = "keywords")
    private List<Alert> alerts = new ArrayList<>();

}
