package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.user;
import tn.esprit.esprobackend.services.IPublicationService;
import tn.esprit.esprobackend.services.IRDIMemberService;
import tn.esprit.esprobackend.services.IRDIService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/Publication")
@CrossOrigin(origins = "http://localhost:4200")

public class PublicationRestController {
    IPublicationService PublicationService;
    IRDIService RDIService;
    IRDIMemberService RDIMemberService;


    @GetMapping("/retrieve-all-Publications")

    public List<Publication> getPublications() {
        List<Publication> listPublications = PublicationService.retrieveAllPublications();
        return listPublications;
    }
    @GetMapping("/retrieve-Publication-Chercheurs/{publicationId}")
    public Set<RDIMember> retrievePublicationChercheurs(@PathVariable("publicationId") Long publicationId) {
        Publication publication = PublicationService.retrievePublication(publicationId);
        Set<RDIMember> chercheurs = publication.getChercheurs();
        return chercheurs;
    }
    @GetMapping("/rdi/{rdiId}")
    public List<Publication> getPublicationsByRdiId(@PathVariable("rdiId") Long rdiId) {
        List<Publication> listPublications = new ArrayList<>(); // Initialize listPublications
        List<RDIMember> chercheurs = RDIMemberService.retrieveAllRDIMembers();
        if (chercheurs != null) {
            for (RDIMember chercheur : chercheurs) {
                if (chercheur.getRDI() != null && chercheur.getRDI().getId().equals(rdiId)) {
                    listPublications.addAll(chercheur.getPublications());
                }
            }
        }
        return listPublications;
    }
    @GetMapping("/rdimember/{rdiMemberid}")
    public List<Publication> getPublicationsByRdiMemberId(@PathVariable("rdiMemberid") Long rdiId) {
        List<Publication> listPublications = new ArrayList<>(); // Initialize listPublications
        List<RDIMember> chercheurs = RDIMemberService.retrieveAllRDIMembers();
        if (chercheurs != null) {
            for (RDIMember chercheur : chercheurs) {
                if ( chercheur.getId().equals(rdiId)) {
                    listPublications.addAll(chercheur.getPublications());
                }
            }
        }
        return listPublications;
    }
    @GetMapping("/rdichercheur/{rdiId}")
    public List<RDIMember> getChercheursbyRdi(@PathVariable("rdiId") Long rdiId) {
        List<RDIMember> chercheurs = RDIMemberService.retrieveAllRDIMembers();
        List<RDIMember> chercheurrdi = new ArrayList<>(); // Initialize listPublications

        if (chercheurs != null) {
            for (RDIMember chercheur : chercheurs) {
                if (chercheur.getRDI() != null && chercheur.getRDI().getId().equals(rdiId)) {
                    chercheurrdi.add(chercheur);
                }
            }
        }        return chercheurrdi;
    }
    @GetMapping("/retrieve-Publication-Chercheurs-ALL")
    public List<RDIMember> retrievePublicationChercheursALL() {
        List<RDIMember> chercheurs = RDIMemberService.retrieveAllRDIMembers();
        return chercheurs;
    }
    @GetMapping("/retrieve-Publication/{Publication-id}")
    public Publication retrievePublication(@PathVariable("Publication-id") Long RDIId) {
        Publication Publication = PublicationService.retrievePublication(RDIId);
        return Publication;
    }
    @GetMapping("/Get-chercheur")
    public List<RDIMember> GetChercheur() {
        List<RDIMember> chercheur = RDIMemberService.retrieveAllRDIMembers();
        return chercheur;
    }
    @GetMapping("/get-RDIs")
    public List<RDI> getRDIs() {
        List<RDI> RDI = RDIService.retrieveAllRDIs();
        return RDI;
    }
    @PostMapping("/add-Publication")
    public Publication addPublication(@RequestBody Publication publication) {
        Set<RDIMember> chercheurs = publication.getChercheurs();
        if (chercheurs != null) {
            for (RDIMember chercheur : chercheurs) {
                chercheur.getPublications().add(publication);
            }
        }

        // Set the updated list of chercheurs back to the publication
        publication.setChercheurs(chercheurs);

        // Ajouter la nouvelle publication
        Publication newPublication = PublicationService.addPublication(publication);

        return newPublication;
    }






    @DeleteMapping("/remove-Publication/{Publication-id}")
    public void removePublication(@PathVariable("Publication-id") Long publicationId) {
            // Fetch the publication by ID
            Publication publication = PublicationService.retrievePublication(publicationId);

            // Remove association with RDIMember records
            for (RDIMember chercheur : publication.getChercheurs()) {
                chercheur.getPublications().remove(publication);
            }

            // Clear the association from the publication side
            publication.getChercheurs().clear();

            // Now delete the publication
            PublicationService.removePublication(publicationId);


    }




    @PutMapping("/modify-Publication")
    public Publication modifyPublication(@RequestBody Publication c) {
        Set<RDIMember> chercheurs = c.getChercheurs();
        if (chercheurs != null) {
            for (RDIMember chercheur : chercheurs) {
                chercheur.getPublications().add(c);
            }
        }

        // Set the updated list of chercheurs back to the publication
        c.setChercheurs(chercheurs);


        // Associer la nouvelle publication à chaque chercheur

        Publication Publication = PublicationService.modifyPublication(c);
        return Publication;
    }
}
