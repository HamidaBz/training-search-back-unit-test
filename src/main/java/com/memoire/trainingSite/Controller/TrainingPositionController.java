package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.TrainingPositionDTO;
import com.memoire.trainingSite.Services.TrainingPositionService;
import com.memoire.trainingSite.models.Company;
import com.memoire.trainingSite.models.PositionStatus;
import com.memoire.trainingSite.models.PositionWorkType;
import com.memoire.trainingSite.models.TrainingPosition;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainingPositions")
public class TrainingPositionController {
    private TrainingPositionService trainingPositionService;

    //dependency injection
    @Autowired
    public TrainingPositionController(TrainingPositionService trainingPositionService) {
        this.trainingPositionService = trainingPositionService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingPositionDTO>> getAllPositions() {
        List<TrainingPositionDTO> positions = trainingPositionService.getAllPositions();
        if (positions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(positions, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingPositionDTO> getPositionById(@PathVariable Long id) {
        Optional<TrainingPositionDTO> position = trainingPositionService.getPositionById(id);
        if (!position.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(position.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<TrainingPosition> createPosition(@RequestBody TrainingPosition trainingPosition) {
        TrainingPosition createdPosition = trainingPositionService.createPosition(trainingPosition);
        if (createdPosition == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(createdPosition, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingPosition> updateUser(@PathVariable Long id, @RequestBody TrainingPosition trainingPosition) {
        TrainingPosition updatedPosition = trainingPositionService.updatePosition(id, trainingPosition);
        if (updatedPosition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            trainingPositionService.deletePosition(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostConstruct
    public void init() {

        Company comp = new Company("aymen comp "  , null , new ArrayList<>());

        TrainingPosition trainingPosition1 = new TrainingPosition(1L, "Software Engineer Intern", LocalDateTime.now(), true, PositionStatus.OPEN, 5, PositionWorkType.REMOTE, LocalDateTime.of(2024, 4, 1, 9, 0), 3, "Bachelor's degree in Computer Science", new ArrayList<>(), new ArrayList<>(), new ArrayList<>() , comp, "https://www.ausbildung.de/uploads/image/75/75d0699e-616b-62e6-42ec-ef403ea4b9f7/" );

        trainingPositionService.createPosition(trainingPosition1);

    }
}
