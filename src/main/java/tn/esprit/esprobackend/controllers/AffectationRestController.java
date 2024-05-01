package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Affectation;
import tn.esprit.esprobackend.services.IAffectationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/affectation")
public class AffectationRestController {
    IAffectationService affectationService;
    // http://localhost:8089/esprobackend/affectation/retrieve-all-affectations
    @GetMapping("/retrieve-all-affectations")
    public List<Affectation> getAffectations() {
        List<Affectation> listAffectations = affectationService.retrieveAllAffectations();
        return listAffectations;
    }
    // http://localhost:8089/esproeackend/affectation/retrieve-affectation/8
    @GetMapping("/retrieve-affectation/{affectation-id}")
    public Affectation retrieveAffectation(@PathVariable("affectation-id") Long chId) {
        Affectation affectation = affectationService.retrieveAffectation(chId);
        return affectation;
    }
    // http://localhost:8089/esprobackend/affectation/add-affectation
    @PostMapping("/add-affectation")
    public Affectation addAffectation(@RequestBody Affectation c) {
        Affectation affectation = affectationService.addAffectation(c);
        return affectation;
    }
    // http://localhost:8089/esprobackend/affectation/remove-affectation/{affectation-id}
    @DeleteMapping("/remove-affectation/{affectation-id}")
    public void removeAffectation(@PathVariable("affectation-id") Long chId) {
        affectationService.removeAffectation(chId);
    }
    // http://localhost:8089/esprobackend/affectation/modify-affectation
    @PutMapping("/modify-affectation")
    public Affectation modifyAffectation(@RequestBody Affectation c) {
        Affectation affectation = affectationService.modifyAffectation(c);
        return affectation;
    }
}

