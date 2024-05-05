package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.services.IPublicationService;
import tn.esprit.esprobackend.services.IRDIMemberService;
import tn.esprit.esprobackend.services.IRDIService;

import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @PreAuthorize("hasAuthority('ADMIN')")
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



    @PreAuthorize("hasAuthority('ADMIN')")


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


    @PreAuthorize("hasAuthority('ADMIN')")

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

    @GetMapping("/activity")
    public ResponseEntity<Map<String, Object>> getActivityData(@RequestParam("rdiId") Long rdiId, @RequestParam("duration") String duration) {
        // Obtain labels based on the selected duration
        List<String> labels = getLabelsBasedOnDuration(duration);

        // Fetch publications for the given RDI ID
        List<Publication> publications = getPublicationsByRdiId(rdiId);

        // Calculate activity by labels
        Map<String, Integer> activityByLabel = PublicationService.calculateActivityByLabels(publications, duration, labels);

        // Get activity data based on labels
        List<Integer> activityData = labels.stream()
                .map(label -> activityByLabel.getOrDefault(label, 0)) // Default to 0 if label not found
                .collect(Collectors.toList());

        // Prepare response data structure
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("labels", labels); // Ensure labels are a list of strings
        responseData.put("data", activityData); // Ensure activity data is a list of integers

        // Return the response entity with correct data structure
        return ResponseEntity.ok(responseData);
    }


    private List<String> getLabelsBasedOnDuration(String duration) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);

        if ("1 mois".equals(duration)) {
            int daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate(); // Get days in the month
            return IntStream.range(1, daysInMonth + 1) // Create a stream from 1 to end of month
                    .mapToObj(i -> "Jour " + i) // Map to "Jour X"
                    .collect(Collectors.toList());
        } else if ("12 mois".equals(duration)) {
            return IntStream.range(0, 12) // Create a stream from 0 to 11
                    .mapToObj(i -> new DateFormatSymbols(Locale.FRANCE).getMonths()[i]) // Get month names
                    .collect(Collectors.toList());
        } else if ("5 ans".equals(duration)) {
            return IntStream.range(currentYear - 4, currentYear + 1) // Create a stream of years
                    .mapToObj(Integer::toString) // Convert to string
                    .collect(Collectors.toList());
        }

        return Collections.singletonList("Année " + currentYear); // Default case
    }


    @GetMapping("/top-rdi-members")
    public List<Map<String, Object>> getTopRDIMembers() {
        return PublicationService.getTopRDIMembers(); // Calculate top RDI members

    }



    // Calculate the difficulty score based on publication type

}


