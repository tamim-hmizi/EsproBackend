package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.Training;
import tn.esprit.esprobackend.services.IRDIService;
import tn.esprit.esprobackend.services.ITrainingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Training")
public class TrainingRestController {
    ITrainingService TrainingService;
    @GetMapping("/retrieve-all-Trainings")
    public List<Training> getTrainings() {
        List<Training> listTrainings = TrainingService.retrieveAllTrainings();
        return listTrainings;
    }

    @GetMapping("/retrieve-Training/{Training-id}")
    public Training retrieveTraining(@PathVariable("Training-id") Long RDIId) {
        Training Training = TrainingService.retrieveTraining(RDIId);
        return Training;
    }
    @PostMapping("/add-Training")
    public Training addTraining(@RequestBody Training c) {
        Training Training = TrainingService.addTraining(c);
        return Training;
    }

    @DeleteMapping("/remove-Training/{Training-id}")
    public void removeTraining(@PathVariable("Training-id") Long RDIId) {
        TrainingService.removeTraining(RDIId);
    }

    @PutMapping("/modify-Training")
    public Training modifyTraining(@RequestBody Training c) {
        Training Training = TrainingService.modifyTraining(c);
        return Training;
    }
}
