package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Training;
import tn.esprit.esprobackend.entities.TrainingRequest;
import tn.esprit.esprobackend.entities.user;
import tn.esprit.esprobackend.repositories.TrainingRepository;
import tn.esprit.esprobackend.repositories.TrainingRequestRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingRequestServiceImpl implements ITrainingRequestService {
    TrainingRequestRepository TrainingRequestRepository;

    @Override
    public List<TrainingRequest> retrieveAllTrainingRequest() {
        return TrainingRequestRepository.findAll();
    }



    @Override
    public TrainingRequest retrieveTrainingRequest(Long TrainingId) {
        return TrainingRequestRepository.findById(TrainingId).get();
    }

    @Override
    public TrainingRequest addTrainingRequest(TrainingRequest c) {
        return TrainingRequestRepository.save(c);
    }

    @Override
    public void removeTrainingRequest(Long TrainingId) {
        TrainingRequestRepository.deleteById(TrainingId);
    }

    @Override
    public TrainingRequest modifyTrainingRequest(TrainingRequest Training) {
        return TrainingRequestRepository.save(Training);
    }

    @Override
    public List<TrainingRequest> searchByTitle(String TitleT) {
        return null;
    }

    @Override
    public List<TrainingRequest> searchByTrainer(user u) {
        return null;
    }


}

