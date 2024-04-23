package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.*;

import java.util.List;

public interface IRDIService {
    public List<RDI> retrieveAllRDIs();
    public RDI retrieveRDI(Long RDIId);
    public RDI addRDI(RDI c);
    public void removeRDI(Long RDIId);
    public RDI modifyRDI(RDI RDI);
    public List<RDI> searchByTitle(String theme);
    public List<RDI> searchByTypeR(String TypeR);
    public List<RDI> searchByPublication(Publication Publication);
    public List<RDI> searchByResearchAxis(ResearchAxis ResearchAxis);
    public List<RDI> searchByPublication(RDIMember RDIMember);

    public List<RDI> searchByKeyWords(String KeyWord);
    public List<RDI> getAllRDIsSortedByDateAsc();
    public void assignResearchAxisToRDI(Long ResearchAxisId, Long RDIId);
    public void assignTrainingToRDI(Long TrainingId, Long RDIId);
    public void assignRDIMemberToRDI( Long RDIId ,RDIMember c);



}
