package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.Services.TrainingPositionService;
import com.memoire.trainingSite.models.TrainingPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainingPositions")
public class TrainingPositionController {
    private TrainingPositionService trainingPositionService;

    @Autowired
    public TrainingPositionController(TrainingPositionService trainingPositionService) {
        this.trainingPositionService = trainingPositionService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingPosition>> getAllPositions() {
        List<TrainingPosition> positions = trainingPositionService.getAllPositions();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @GetMapping("/{positionId}")
    public ResponseEntity<TrainingPosition> getPositionById(@PathVariable Long positionId) {
        Optional<TrainingPosition> position = trainingPositionService.getPositionById(positionId);
        return position.map(tp -> new ResponseEntity<>(tp, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TrainingPosition> createPosition(@RequestBody TrainingPosition trainingPosition) {
        TrainingPosition createdPosition = trainingPositionService.createPosition(trainingPosition);
        return new ResponseEntity<>(createdPosition, HttpStatus.CREATED);
    }

    @PutMapping("/{positionId}")
    public ResponseEntity<TrainingPosition> updatePosition(@PathVariable Long positionId,
                                                       @RequestBody TrainingPosition trainingPosition) {
        Optional<TrainingPosition> updatedPosition =
                trainingPositionService.updatePosition(positionId, trainingPosition);
        return updatedPosition.map(tp ->  new ResponseEntity<>(tp, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{positionId}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long positionId) {
        trainingPositionService.deletePosition(positionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
