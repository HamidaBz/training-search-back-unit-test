package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.TrainingPositionRepo;
import com.memoire.trainingSite.models.TrainingPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingPositionService {
    private TrainingPositionRepo trainingPositionRepo ;

    @Autowired
    public  TrainingPositionService(TrainingPositionRepo trainingPositionRepo){
        this.trainingPositionRepo = trainingPositionRepo ;
    }

    public List<TrainingPosition> getAllPositions() {
        return trainingPositionRepo.findAll();
    }


    public TrainingPosition createPosition(TrainingPosition training_position) {
        return trainingPositionRepo.save(training_position);
    }

    public Optional<TrainingPosition> getPositionById(Long position_id) {
        return  trainingPositionRepo.findById(position_id);
    }
    public Optional<TrainingPosition> updatePosition(Long position_id, TrainingPosition trainingPosition) {
        Optional<TrainingPosition> existingPosition = trainingPositionRepo.findById(position_id);
        if (existingPosition.isPresent()) {
            return Optional.of(trainingPositionRepo.save(trainingPosition));
        }
        return Optional.empty();
    }
    public void deletePosition(Long position_id) {
        trainingPositionRepo.deleteById(position_id);
    }
}
