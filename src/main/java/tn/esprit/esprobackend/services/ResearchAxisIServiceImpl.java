package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.ResearchAxis;
import tn.esprit.esprobackend.repositories.PublicationIRepository;
import tn.esprit.esprobackend.repositories.ResearchAxisIRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ResearchAxisIServiceImpl implements IResearchAxisService {
    ResearchAxisIRepository ResearchAxisRepository;

    @Override
    public List<ResearchAxis> retrieveAllResearchAxis() {
        return ResearchAxisRepository.findAll();
    }

    @Override
    public ResearchAxis retrieveResearchAxis(Long Id) {
        return ResearchAxisRepository.findById(Id).get();
    }

    @Override
    public ResearchAxis addResearchAxis(ResearchAxis c) {
        return ResearchAxisRepository.save(c);
    }

    @Override
    public void removeResearchAxis(Long Id) {
        ResearchAxisRepository.deleteById(Id);
    }

    @Override
    public ResearchAxis modifyResearchAxis(ResearchAxis ResearchAxis) {
        return ResearchAxisRepository.save(ResearchAxis);
    }

    @Override
    public List<ResearchAxis> searchByTitle(String SubjectRA) {
        return null;
    }
}

