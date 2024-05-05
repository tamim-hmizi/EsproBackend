package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.ResearchAxis;

import java.util.List;

public interface IResearchAxisService {
    public List<ResearchAxis> retrieveAllResearchAxis();
    public ResearchAxis retrieveResearchAxis(Long Id);
    public ResearchAxis addResearchAxis(ResearchAxis c);
    public void removeResearchAxis(Long Id);
    public ResearchAxis modifyResearchAxis(ResearchAxis ResearchAxis);
    public List<ResearchAxis> searchByTitle(String SubjectRA);

}
