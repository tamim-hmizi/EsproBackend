package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Event;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.ResearchAxis;
import tn.esprit.esprobackend.services.IEventService;
import tn.esprit.esprobackend.services.IRDIMemberService;
import tn.esprit.esprobackend.services.IRDIService;
import tn.esprit.esprobackend.services.IResearchAxisService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/RDI")
@CrossOrigin(origins = "http://localhost:4200")
public class RDIRestController {
    IRDIService RDIService;
    IRDIMemberService IRDIMemberService;
    IResearchAxisService ResearchAxisService;
    @GetMapping("/retrieve-all-RDIs")
    public List<RDI> getRDIs() {
        List<RDI> listRDIs = RDIService.retrieveAllRDIs();
        return listRDIs;
    }
    @GetMapping("/retrieve-all-ResearchAxis")
    public List<ResearchAxis> getresearchaxisAll() {
        List<ResearchAxis> ResearchAxiss = ResearchAxisService.retrieveAllResearchAxis();
        return ResearchAxiss;
    }
    @GetMapping("/retrieve-RDI/{RDI-id}")
    public RDI retrieveRDI(@PathVariable("RDI-id") Long RDIId) {
        RDI RDI = RDIService.retrieveRDI(RDIId);
        return RDI;
    }
    @PostMapping("/add-RDI")
    public RDI addRDI(@RequestBody RDI c) {
        RDI RDI = RDIService.addRDI(c);
        return RDI;
    }

    @DeleteMapping("/remove-RDI/{RDI-id}")
    public void removeRDI(@PathVariable("RDI-id") Long RDIId) {
        RDIService.removeRDI(RDIId);
    }

    @PutMapping("/modify-RDI")
    public RDI modifyRDI(@RequestBody RDI c) {
        RDI RDI = RDIService.modifyRDI(c);
        return RDI;
    }
    @PutMapping("/affecter-ResearchAxis-a-RDI/{RDI-id}/{ResearchAxis-id}")
    public void affecterResearchAxisARDI(@PathVariable("RDI-id") Long RDIid,
                                             @PathVariable("ResearchAxis-id") Long ResearchAxisid) {
        RDIService.assignResearchAxisToRDI(ResearchAxisid,RDIid);
    }

    @PutMapping("/affecter-Training-a-RDI/{RDI-id}/{Training-id}")
    public void affecterTrainingARDI(@PathVariable("RDI-id") Long RDIid,
                                        @PathVariable("Training-id") Long Trainingid) {
        RDIService.assignTrainingToRDI(RDIid, Trainingid);
    }
    @PutMapping("/affecter-Training-a-RDI/{RDI-id}")
    public void affecterRDIMemberARDI(@PathVariable("RDI-id") Long RDIid,@RequestBody RDIMember c
                                    ) {
        RDIMember RDIMember = IRDIMemberService.addRDIMember(c);
        RDIService.assignRDIMemberToRDI(RDIid,RDIMember);



    }
}
