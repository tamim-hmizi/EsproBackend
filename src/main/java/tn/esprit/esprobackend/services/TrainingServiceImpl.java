package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.*;
import tn.esprit.esprobackend.repositories.RDIRepository;
import tn.esprit.esprobackend.repositories.TrainingRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements ITrainingService {
    TrainingRepository TrainingRepository;

    @Override
    public List<Training> retrieveAllTrainings() {
        return TrainingRepository.findAll();
    }

    @Override
    public Training retrieveTraining(Long TrainingId) {
        return TrainingRepository.findById(TrainingId).get();
    }

    @Override
    public Training addTraining(Training c) {
        return TrainingRepository.save(c);
    }

    @Override
    public void removeTraining(Long TrainingId) {
        TrainingRepository.deleteById(TrainingId);
    }

    @Override
    public Training modifyTraining(Training Training) {
        return TrainingRepository.save(Training);
    }

    @Override
    public List<Training> searchByTitle(String TitleT) {
        return null;
    }

    @Override
    public List<Training> searchByTrainer(user u) {
        return null;
    }

    @Override
    public List<Training> getAllEventsSortedByDateAsc() {
        return null;
    }
}

