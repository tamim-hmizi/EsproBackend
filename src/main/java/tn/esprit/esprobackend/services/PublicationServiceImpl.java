package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.ResearchAxis;
import tn.esprit.esprobackend.repositories.PublicationIRepository;
import tn.esprit.esprobackend.repositories.RDIRepository;
import tn.esprit.esprobackend.repositories.RDImemberRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements IPublicationService {
    PublicationIRepository PublicationRepository;
    RDImemberRepository RDImemberRepository;

    @Override
    public List<Publication> retrieveAllPublications() {
        return PublicationRepository.findAll();
    }

    @Override
    public Publication retrievePublication(Long Id) {
        return PublicationRepository.findById(Id).get();
    }

    @Override
    public Publication addPublication(Publication c) {
        return PublicationRepository.save(c);
    }

    @Override
    public void removePublication(Long Id) {
        PublicationRepository.deleteById(Id);
    }

    @Override
    public Publication modifyPublication(Publication Publication) {
        return PublicationRepository.save(Publication);
    }

    @Override
    public List<Publication> searchByTitle(String Subject) {
        return null;
    }

    @Override
    public List<Publication> getAllPublicationsByRDIs(RDI RDI) {
        return null;
    }

    @Override
    public List<Publication> getAllPublicationsByuser(RDIMember RDIMember) {
        return null;
    }


    @Override
    public List<Publication> getAllPublicationsSortedByDateAsc() {
        return null;
    }
}

