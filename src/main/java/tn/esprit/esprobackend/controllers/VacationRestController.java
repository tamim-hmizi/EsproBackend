package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Vacation;
import tn.esprit.esprobackend.services.IVacationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vacation")
public class VacationRestController {
    IVacationService vacationService;
    // http://localhost:8089/esprobackend/vacation/retrieve-all-vacations
    @GetMapping("/retrieve-all-vacations")
    public List<Vacation> getVacations() {
        List<Vacation> listVacations = vacationService.retrieveAllVacations();
        return listVacations;
    }
    // http://localhost:8089/esprobackend/vacation/retrieve-vacation/8
    @GetMapping("/retrieve-vacation/{vacation-id}")
    public Vacation retrieveVacation(@PathVariable("vacation-id") Long chId) {
        Vacation vacation = vacationService.retrieveVacation(chId);
        return vacation;
    }
    // http://localhost:8089/esprobackend/vacation/add-vacation
    @PostMapping("/add-vacation")
    public Vacation addVacation(@RequestBody Vacation c) {
        Vacation vacation = vacationService.addVacation(c);
        return vacation;
    }
    // http://localhost:8089/esprobackend/vacation/remove-vacation/{vacation-id}
    @DeleteMapping("/remove-vacation/{vacation-id}")
    public void removeVacation(@PathVariable("vacation-id") Long chId) {
        vacationService.removeVacation(chId);
    }
    // http://localhost:8089/esprobackend/vacation/modify-vacation
    @PutMapping("/modify-vacation")
    public Vacation modifyVacation(@RequestBody Vacation c) {
        Vacation vacation = vacationService.modifyVacation(c);
        return vacation;
    }
}
