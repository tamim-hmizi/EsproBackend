package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Vacation;
import tn.esprit.esprobackend.repositories.VacationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class VacationServiceImpl implements IVacationService {
    VacationRepository vacationRepository;
    public List<Vacation> retrieveAllVacations() {
        return vacationRepository.findAll();
    }
    public Vacation retrieveVacation(Long id) {
        return vacationRepository.findById(id).get();
    }
    public Vacation addVacation(Vacation c) {
        return vacationRepository.save(c);
    }
    public void removeVacation(Long id) {
        vacationRepository.deleteById(id);
    }
    public Vacation modifyVacation(Vacation bloc) {
        return vacationRepository.save(bloc);
    }
}

