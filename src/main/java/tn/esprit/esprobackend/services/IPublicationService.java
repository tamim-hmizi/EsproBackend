package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.user;

import java.util.List;

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
}
