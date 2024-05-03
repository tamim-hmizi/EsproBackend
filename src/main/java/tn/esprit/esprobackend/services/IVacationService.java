package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Vacation;

import java.util.List;
public interface IVacationService {
    public List<Vacation> retrieveAllVacations();
    public Vacation retrieveVacation(Long VacationId);
    public Vacation addVacation(Vacation c);
    public void removeVacation(Long VacationId);
    public Vacation modifyVacation(Vacation Vacation);
}
