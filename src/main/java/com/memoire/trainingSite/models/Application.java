package com.memoire.trainingSite.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@AssociationOverrides({
        @AssociationOverride(name = "id_application.position", joinColumns = @JoinColumn(name ="position_id")),
        @AssociationOverride(name = "id_application.applicant", joinColumns = @JoinColumn(name = "user_id"))
}
)
public class Application {
    @EmbeddedId
    private ApplicationId id_application = new ApplicationId();

    @Column(name = "application_date")
    private LocalDateTime application_date;
    @Column(name = "application_motivation")
    private String application_motivation;
    @Column(name = "application_status")
    private ApplicationStatus application_status;

}
