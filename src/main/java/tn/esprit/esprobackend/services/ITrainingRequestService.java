package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.*;

import java.util.List;

public interface ITrainingRequestService {
    public List<TrainingRequest> retrieveAllTrainingRequest();
    public TrainingRequest retrieveTrainingRequest(Long Id);
    public TrainingRequest addTrainingRequest(TrainingRequest c);
    public void removeTrainingRequest(Long Id);
    public TrainingRequest modifyTrainingRequest(TrainingRequest TrainingRequest);
    public List<TrainingRequest> searchByTitle(String theme);
    public List<TrainingRequest> searchByTrainer(user user);


}
