package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.user;
import tn.esprit.esprobackend.services.IRDIMemberService;
import tn.esprit.esprobackend.services.IRDIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/RDIMember")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAuthority('ADMIN')")

public class RDIMemberRestController {
    IRDIMemberService RDIMemberService;
    IRDIService IRDIService;
    @GetMapping("/retrieve-all-RDIMembers")
    public List<RDIMember> getRDIMembers() {
        List<RDIMember> listRDIMembers = RDIMemberService.retrieveAllRDIMembers();
        return listRDIMembers;
    }
    @GetMapping("/retreive-rdiMember/{rdiId}")
    public List<RDIMember> getRDIMembersByRdiId(@PathVariable("rdiId") Long publicationId)
    {        List<RDIMember> listPublications = new ArrayList<>(); // Initialize listPublications

        List<RDIMember> listRDIMembers = RDIMemberService.retrieveAllRDIMembers();
        RDIMember RDIthet = null;
        RDI RDI = IRDIService.retrieveRDI(publicationId);
        if (listRDIMembers != null) {
            for (RDIMember chercheur : listRDIMembers) {
                if ( chercheur.getRDI()==RDI) {
                    listPublications.add(chercheur) ;
                }
            }
        }        return listPublications;
    }
    @GetMapping("/retrieve-rdi/{rdiId}")
    public RDI getRDI(@PathVariable("rdiId") Long rdiId) {
        // Retrieve the RDI by ID
        RDI rdi = IRDIService.retrieveRDI(rdiId);

        // If the RDI is found, check if any RDIMember is associated with it
return rdi ;

    }


    @GetMapping("/retrieve-RDIMember/{RDI-id}")
    public RDIMember retrieveRDI(@PathVariable("RDI-id") Long RDIId) {
        RDIMember RDIMember = RDIMemberService.retrieveRDIMember(RDIId);

        return RDIMember;
    }
    @PostMapping("/add-RDIMember/{rdiId}")
    public RDIMember addRDIMember(@RequestBody RDIMember c,@PathVariable("rdiId") Long rdiId) {
        c.setRDI(this.getRDI(rdiId));
        RDIMember RDIMember = RDIMemberService.addRDIMember(c);
        return RDIMember;
    }

    @GetMapping("/retrieve-User-ALL")
    public  List<user> getUsersAll() {
       List<user> listRDIMembers = RDIMemberService.retrieveuserAll();
        return listRDIMembers;
    }
    @DeleteMapping("/remove-RDIMember/{RDIMember-id}")
    public void removeRDIMember(@PathVariable("RDIMember-id") Long RDIMemberId) {
        RDIMemberService.removeRDIMember(RDIMemberId);
    }

    @PutMapping("/modify-RDIMember")
    public RDIMember modifyRDIMember(@RequestBody RDIMember c) {
        RDIMember RDIMember = RDIMemberService.modifyRDIMember(c);
        return RDIMember;
    }
}
