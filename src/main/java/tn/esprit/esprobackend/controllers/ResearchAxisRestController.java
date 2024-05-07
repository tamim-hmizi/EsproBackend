package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.ResearchAxis;
import tn.esprit.esprobackend.services.IPublicationService;
import tn.esprit.esprobackend.services.IRDIMemberService;
import tn.esprit.esprobackend.services.IRDIService;
import tn.esprit.esprobackend.services.IResearchAxisService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/ResearchAxis")
@CrossOrigin(origins = "http://localhost:4200")
//@PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER') ")
public class ResearchAxisRestController {
    IResearchAxisService ResearchAxisService;
    IRDIService IRDIService;
    @GetMapping("/retrieve-all-ResearchAxiss")
    public List<ResearchAxis> getResearchAxiss() {
        List<ResearchAxis> listResearchAxiss = ResearchAxisService.retrieveAllResearchAxis();
        return listResearchAxiss;
    }
    @GetMapping("/rdi/{rdiId}")
    public Set<ResearchAxis> getresearchAxisByRdiId(@PathVariable("rdiId") Long rdiId) {
        RDI listRDI = IRDIService.retrieveRDI(rdiId); // Initialize listPublications
            Set<ResearchAxis> chercheurs = listRDI.getResearchAxis();

        return chercheurs;
    }
    @GetMapping("/retrieve-ResearchAxis/{ResearchAxis-id}")
    public ResearchAxis retrieveResearchAxis(@PathVariable("ResearchAxis-id") Long RDIId) {
        ResearchAxis ResearchAxis = ResearchAxisService.retrieveResearchAxis(RDIId);
        return ResearchAxis;
    }
    @PostMapping("/add-ResearchAxis/{id}")
    public ResearchAxis addResearchAxis(@RequestBody ResearchAxis c,@PathVariable("id") Long RDIId) {
        ResearchAxis ResearchAxis = ResearchAxisService.addResearchAxis(c);
        RDI rdi = IRDIService.retrieveRDI(RDIId);
        rdi.getResearchAxis().add(ResearchAxis);
rdi.setResearchAxis(rdi.getResearchAxis());
        IRDIService.modifyRDI(rdi);
        return ResearchAxis;
    }

    @DeleteMapping("/remove-ResearchAxis/{ResearchAxis-id}")
    public void removeResearchAxis(@PathVariable("ResearchAxis-id") Long RDIId) {
        List<RDI> rdis = IRDIService.retrieveAllRDIs();

        // Remove association with RDIMember records
        for (RDI rdi :rdis) {
            for (ResearchAxis ResearchAxis :rdi.getResearchAxis()) {
if (ResearchAxis.getId().equals(RDIId)){                rdi.getResearchAxis().remove(ResearchAxis);
};            }
        }

        // Clear the association from the publication side
        ResearchAxisService.removeResearchAxis(RDIId);
    }

    @PutMapping("/modify-ResearchAxis")
    public ResearchAxis modifyResearchAxis(@RequestBody ResearchAxis c) {

        ResearchAxis ResearchAxis = ResearchAxisService.modifyResearchAxis(c);
        return ResearchAxis;
    }
}
