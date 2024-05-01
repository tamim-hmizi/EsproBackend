package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.*;
import tn.esprit.esprobackend.repositories.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class RDIServiceImpl implements IRDIService {
    RDIRepository RDIRepositorys;
    ResearchAxisIRepository ResearchAxisRepository;
    PublicationIRepository PublicationRepository;
    TrainingRepository TrainingRepository;
    RDImemberRepository RDIMemberRepository;



    @Override
    public List<RDI> retrieveAllRDIs() {
        return RDIRepositorys.findAll();
    }

    @Override
    public RDI retrieveRDI(Long RDIId) {
        return RDIRepositorys.findById(RDIId).get();
    }

    @Override
    public RDI addRDI(RDI c) {
        return RDIRepositorys.save(c);
    }

    @Override
    public void removeRDI(Long RDIId) {
        RDIRepositorys.deleteById(RDIId);

    }

    @Override
    public RDI modifyRDI(RDI RDI) {
        return  RDIRepositorys.save(RDI);
    }

    @Override
    public List<RDI> searchByTitle(String theme) {
        return null;
    }


    public void assignTrainingToRDI(Long TrainingId, Long RDIId) {
        RDI RDI = RDIRepositorys.findById(RDIId).get();
        Training Training = TrainingRepository.findById(TrainingId).get();
        Set<Training> Traininglist =RDI.getTrainings();
        // on set le fils dans le parent :
        Traininglist.add(Training);
        RDI.setTrainings(Traininglist);
        RDIRepositorys.save(RDI);
    }

    @Override
    public List<RDI> searchByTypeR(String TypeR) {
        return null;
    }

    @Override
    public List<RDI> searchByPublication(Publication Publication) {
        return null;
    }

    @Override
    public List<RDI> searchByResearchAxis(ResearchAxis ResearchAxis) {
        return null;
    }

    @Override
    public List<RDI> searchByPublication(RDIMember RDIMember) {
        return null;
    }

    @Override
    public List<RDI> searchByKeyWords(String KeyWord) {
        return null;
    }

    @Override
    public List<RDI> getAllRDIsSortedByDateAsc() {
        return null;
    }


    public void assignResearchAxisToRDI(Long ResearchAxisId, Long RDIId) {
        RDI RDI = RDIRepositorys.findById(RDIId).get();
        ResearchAxis ResearchAxis = ResearchAxisRepository.findById(ResearchAxisId).get();
        Set<ResearchAxis> ResearchAxislist =RDI.getResearchAxis();
        // on set le fils dans le parent :
        ResearchAxislist.add(ResearchAxis);
        RDI.setResearchAxis(ResearchAxislist);
        RDIRepositorys.save(RDI);
    }
    public void assignRDIMemberToRDI( Long RDIId, RDIMember Member) {
        RDI RDI = RDIRepositorys.findById(RDIId).get();
        Member.setRDI(RDI);
        RDIMemberRepository.save(Member);
        RDI.getRDIMembers().add(Member);



        RDIRepositorys.save(RDI);
    }

    public boolean existsByTheme( String theme) {

      return  RDIRepositorys.existsByTheme(theme);
    }
    public Map<String, List<Integer>> getActivityData(String duration) {
        // This is where you'd implement your logic to get the data based on the duration
        // Example data structure
        Map<String, List<Integer>> activityData = new HashMap<>();

        // Example: populate the map with data
        // You'll need to implement your logic for fetching and processing the data here

        return activityData;
    }
}

