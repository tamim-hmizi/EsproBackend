package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Affectation;
import java.util.List;
public interface IAffectationService {
    public List<Affectation> retrieveAllAffectations();
    public Affectation retrieveAffectation(Long AffectationId);
    public Affectation addAffectation(Affectation c);
    public void removeAffectation(Long AffectationId);
    public Affectation modifyAffectation(Affectation Affectation);
}
