package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.services.ISponsorService;
import tn.esprit.esprobackend.entities.Sponsor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Sponsor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SponsorRestController {

    ISponsorService sponsorservice;

    @GetMapping("/retrieve-all-Sponsors")
    public List<Sponsor> getSponsors() {
        return sponsorservice.retrieveAllSponsors();
    }

    @GetMapping("/retrieve-Sponsor/{Sponsor-id}")
    public Sponsor retrieveSponsor(@PathVariable("Sponsor-id") Long spId) {
        return sponsorservice.retrieveSponsor(spId);
    }

    @PostMapping("/add-Sponsor")
    public Sponsor addSponsor(@RequestBody Sponsor c) {
        return sponsorservice.addSponsor(c);
    }

    @DeleteMapping("/remove-Sponsor/{Sponsor-id}")
    public void removeSponsor(@PathVariable("Sponsor-id") Long spId) {
        sponsorservice.removeSponsor(spId);
    }

    @PutMapping("/modify-Sponsor")
    public Sponsor modifySponsor(@RequestBody Sponsor c) {
        return sponsorservice.modifySponsor(c);
    }
}
