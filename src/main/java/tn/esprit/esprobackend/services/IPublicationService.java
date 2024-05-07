package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.*;

import java.util.List;
import java.util.Map;

public interface IPublicationService {
    public List<Publication> retrieveAllPublications();
    public Publication retrievePublication(Long Id);
    public Publication addPublication(Publication c);
    public void removePublication(Long Id);
    public Publication modifyPublication(Publication Publication);
    public List<Publication> searchByTitle(String Subject);
    public List<Publication> getAllPublicationsByRDIs(RDI RDI);
    public List<Publication> getAllPublicationsByuser(RDIMember RDIMember);

    public List<Publication> getAllPublicationsSortedByDateAsc();
    public int getDifficultyScore(TypeP type);
    public Map<String, Integer> calculateActivityByLabels(List<Publication> publications, String duration, List<String> labels);
    public List<Map<String, Object>> getTopRDIMembers();}
