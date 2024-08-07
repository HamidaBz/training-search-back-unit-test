package com.memoire.trainingSite.DTO;

import com.memoire.trainingSite.models.ApplicationId;
import com.memoire.trainingSite.models.ApplicationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApplicationDTO {

    private Integer id_application ;
    private LocalDateTime application_date;
    private String application_motivation;
    private ApplicationStatus application_status;
}
