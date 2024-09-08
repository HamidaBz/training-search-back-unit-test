package com.memoire.trainingSite.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ApplicationId implements Serializable {

    @ManyToOne
    private TrainingPosition position;
    @ManyToOne
    private Applicant applicant;
}
