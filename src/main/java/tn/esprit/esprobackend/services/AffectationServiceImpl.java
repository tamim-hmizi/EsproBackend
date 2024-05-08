package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Affectation;
import tn.esprit.esprobackend.repositories.AffectationRepository;


import java.util.List;

@Service
@AllArgsConstructor
public class AffectationServiceImpl implements IAffectationService {
    AffectationRepository affectationRepository;
    public List<Affectation> retrieveAllAffectations() {
        return affectationRepository.findAll();
    }
    public Affectation retrieveAffectation(Long id) {
        return affectationRepository.findById(id).get();
    }
    public Affectation addAffectation(Affectation c) {
        return affectationRepository.save(c);
    }
    public void removeAffectation(Long id) {
        affectationRepository.deleteById(id);
    }
    public Affectation modifyAffectation(Affectation bloc) {
        return affectationRepository.save(bloc);
    }
}

