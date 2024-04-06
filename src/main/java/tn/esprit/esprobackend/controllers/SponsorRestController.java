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
        List<Sponsor> listSponsors = sponsorservice.retrieveAllSponsors();
        return listSponsors;
    }

    @GetMapping("/retrieve-Sponsor/{Sponsor-id}")
    public Sponsor retrieveSponsor(@PathVariable("Sponsor-id") Long spId) {
        Sponsor Sponsor = sponsorservice.retrieveSponsor(spId);
        return Sponsor;
    }
    @PostMapping("/add-Sponsor")
    public Sponsor addSponsor(@RequestBody Sponsor c) {
        Sponsor Sponsor = sponsorservice.addSponsor(c);
        return Sponsor;
    }
    // http://localhost:8089/tpfoyer/Sponsor/remove-Sponsor/{Sponsor-id}
    @DeleteMapping("/remove-Sponsor/{Sponsor-id}")
    public void removeSponsor(@PathVariable("Sponsor-id") Long spId) {
        sponsorservice.removeSponsor(spId);
    }
    // http://localhost:8089/tpfoyer/Sponsor/modify-Sponsor
    @PutMapping("/modify-Sponsor")
    public Sponsor modifySponsor(@RequestBody Sponsor c) {
        Sponsor Sponsor = sponsorservice.modifySponsor(c);
        return Sponsor;
    }
}
