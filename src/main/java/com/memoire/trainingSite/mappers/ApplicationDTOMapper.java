package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.ApplicationDTO;
import com.memoire.trainingSite.models.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDTOMapper {
    public ApplicationDTO toDTO(Application application) {
        return new ApplicationDTO(
                Integer.parseInt(String.valueOf(application.getId_application())),
                application.getApplication_date(),
                application.getApplication_motivation(),
                application.getApplication_status()
        );
    }
}
