package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.TrainingPositionRepo;
import com.memoire.trainingSite.DTO.TrainingPositionDTO;
import com.memoire.trainingSite.mappers.TrainingPositionDTOMapper;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.TrainingPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingPositionService {
    private TrainingPositionRepo trainingPositionRepo ;
    private TrainingPositionDTOMapper trainingPositionDTOMapper ;

    @Autowired
    public  TrainingPositionService(TrainingPositionRepo trainingPositionRepo , TrainingPositionDTOMapper trainingPositionDTOMapper){
        this.trainingPositionRepo = trainingPositionRepo ;
        this.trainingPositionDTOMapper = trainingPositionDTOMapper ;
    }

    public List<TrainingPositionDTO> getAllPositions() {
        return trainingPositionRepo.findAll().stream().map(t -> trainingPositionDTOMapper.toDTO(t)).collect(Collectors.toList()) ;
    }


    public TrainingPosition createPosition(TrainingPosition training_position) {
        return trainingPositionRepo.save(training_position);
    }

    public Optional<TrainingPositionDTO> getPositionById(Long id) {
        return  trainingPositionRepo.findById(id).map(trainingPositionDTOMapper::toDTO) ;
    }
    public TrainingPosition updatePosition(Long id, TrainingPosition trainingPosition) {
        if (trainingPositionRepo.existsById(id)) {
            trainingPosition.setPosition_id(id);
            return trainingPositionRepo.save(trainingPosition);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
    }
    public void deletePosition(Long id) {
        trainingPositionRepo.deleteById(id);
    }
}
