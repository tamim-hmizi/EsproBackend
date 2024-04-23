package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.TrainingRequest;
import tn.esprit.esprobackend.services.ITrainingRequestService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/TrainingRequest")
public class TrainingRequestRestController {
    ITrainingRequestService TrainingRequestService;
    @GetMapping("/retrieve-all-TrainingRequests")
    public List<TrainingRequest> getTrainingRequests() {
        List<TrainingRequest> listTrainingRequests = TrainingRequestService.retrieveAllTrainingRequest();
        return listTrainingRequests;
    }

    @GetMapping("/retrieve-Training/{Training-id}")
    public TrainingRequest retrieveTraining(@PathVariable("Training-id") Long RDIId) {
        TrainingRequest TrainingRequest = TrainingRequestService.retrieveTrainingRequest(RDIId);
        return TrainingRequest;
    }
    @PostMapping("/add-TrainingRequest")
    public TrainingRequest addTrainingRequest(@RequestBody TrainingRequest c) {
        TrainingRequest TrainingRequest = TrainingRequestService.addTrainingRequest(c);
        return TrainingRequest;
    }

    @DeleteMapping("/remove-TrainingRequest/{TrainingRequest-id}")
    public void removeTrainingRequest(@PathVariable("TrainingRequest-id") Long RDIId) {
        TrainingRequestService.removeTrainingRequest(RDIId);
    }

    @PutMapping("/modify-TrainingRequest")
    public TrainingRequest modifyTrainingRequest(@RequestBody TrainingRequest c) {
        TrainingRequest TrainingRequest = TrainingRequestService.modifyTrainingRequest(c);
        return TrainingRequest;
    }
}
