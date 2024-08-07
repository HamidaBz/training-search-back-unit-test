package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.TrainingPositionDTO;
import com.memoire.trainingSite.models.Region;
import com.memoire.trainingSite.models.TrainingPosition;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class TrainingPositionDTOMapper {
    public TrainingPositionDTO toDTO(TrainingPosition trainingPosition) {
        return new TrainingPositionDTO(
                trainingPosition.getPosition_id(),
                trainingPosition.getPosition_description(),
                trainingPosition.getPosition_posting_date(),
                trainingPosition.getPosition_visibility(),
                trainingPosition.getPosition_status().toString(),
                trainingPosition.getPosition_free_places(),
                trainingPosition.getPosition_work_type(),
                trainingPosition.getPosition_starting_date(),
                trainingPosition.getPosition_duration(),
                trainingPosition.getPosition_requirements(),
                trainingPosition.getPosition_image(),
                trainingPosition.getRegions()
                        .stream()
                        .map(Region::getRegion_name)
                        .collect(Collectors.toList()),

                trainingPosition.getNotifications() ,
                trainingPosition.getCompany().getCompanyName()
        );
    }
}
