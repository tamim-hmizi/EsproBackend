package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Event;
import tn.esprit.esprobackend.entities.Training;
import tn.esprit.esprobackend.entities.user;

import java.util.List;

public interface ITrainingService {
    public List<Training> retrieveAllTrainings();
    public Training retrieveTraining(Long TrainingId);
    public Training addTraining(Training c);
    public void removeTraining(Long TrainingId);
    public Training modifyTraining(Training Training);
    public List<Training> searchByTitle(String TitleT);
    public List<Training> searchByTrainer(user u);

    public List<Training> getAllEventsSortedByDateAsc();
}
