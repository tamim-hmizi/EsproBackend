package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.*;
import tn.esprit.esprobackend.services.IPublicationService;
import tn.esprit.esprobackend.services.IRDIMemberService;
import tn.esprit.esprobackend.services.IRDIService;
import tn.esprit.esprobackend.services.IResearchAxisService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/RDI")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class RDIRestController {
    IRDIService RDIService;
    IRDIMemberService IRDIMemberService;
    IResearchAxisService ResearchAxisService;
    PublicationRestController PublicationRestController;
    IPublicationService publicationService;
    IUserService IUserService;
    @GetMapping("/retrieve-all-RDIs")
    public List<RDI> getRDIs() {
        List<RDI> listRDIs = RDIService.retrieveAllRDIs();
        return listRDIs;
    }

    @GetMapping("/retrieve-User-RDIMember/{userId}")
    public RDIMember getRDI(@PathVariable long userId) {

        RDIMember RDIMember = IRDIMemberService.findRDIMemberByUser(IUserService.retrieveUser(userId)
        );
        return RDIMember;
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
    @PreAuthorize("hasAuthority('ADMIN')")

    @PostMapping("/add-RDI")
    public RDI addRDI(@RequestBody RDI c) {
        RDI RDI = RDIService.addRDI(c);
        return RDI;
    }
    @GetMapping("/retrieve-RDIMembers/{rdiId}")
    public List<RDIMember> getRDIMembersByRdiId(@PathVariable("rdiId") Long publicationId)
    {        List<RDIMember> listPublications = new ArrayList<>(); // Initialize listPublications

        List<RDIMember> listRDIMembers = IRDIMemberService.retrieveAllRDIMembers();
        RDIMember RDIthet = null;
        RDI RDI = this.retrieveRDI(publicationId);
        if (listRDIMembers != null) {
            for (RDIMember chercheur : listRDIMembers) {
                if ( chercheur.getRDI()==RDI) {
                    listPublications.add(chercheur) ;
                }
            }
        }        return listPublications;
    }

    @GetMapping("/retrieve-RDIPublication/{rdiId}")
    public List<Publication> retrieveRDIPublication(@PathVariable("rdiId") Long publicationId)
    {
        List<Publication> listRDIMembers = PublicationRestController.getPublicationsByRdiId(publicationId);

              return listRDIMembers;
    }
    @PreAuthorize("hasAuthority('ADMIN')")

    @DeleteMapping("/remove-RDI/{RDI-id}")
    public void removeRDI(@PathVariable("RDI-id") Long RDIId) {
        RDIService.removeRDI(RDIId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")

    @PutMapping("/modify-RDI")
    public RDI modifyRDI(@RequestBody RDI c) {
        RDI RDI = RDIService.modifyRDI(c);
        return RDI;
    }
    @PreAuthorize("hasAuthority('ADMIN')")

    @PutMapping("/affecter-ResearchAxis-a-RDI/{RDI-id}/{ResearchAxis-id}")
    public void affecterResearchAxisARDI(@PathVariable("RDI-id") Long RDIid,
                                             @PathVariable("ResearchAxis-id") Long ResearchAxisid) {
        RDIService.assignResearchAxisToRDI(ResearchAxisid,RDIid);
    }


    @PutMapping("/affecter-Training-a-RDI/{RDI-id}")
    public void affecterRDIMemberARDI(@PathVariable("RDI-id") Long RDIid,@RequestBody RDIMember c
                                    ) {
        RDIMember RDIMember = IRDIMemberService.addRDIMember(c);
        RDIService.assignRDIMemberToRDI(RDIid,RDIMember);



    }

    @GetMapping("/most-similar")
    public List<RDI> getMostSimilarRDIs(@RequestParam("targetRDIId") Long targetRDIId) {
        return RDIService.getMostSimilarRDIs(targetRDIId); // Return the most similar RDIs
    }
    @GetMapping("/check-theme/{theme}")

    public ResponseEntity<Boolean> checkThemeExists(@PathVariable String theme) {
        boolean exists = RDIService.existsByTheme(theme);
        return ResponseEntity.ok(exists); // Renvoie true si le thème existe, sinon false
    }
    @GetMapping("/activity")
    public ResponseEntity<Map<String, List<Integer>>> getActivityData(@RequestParam("duration") String duration) {
        Map<String, List<Integer>> activityData = RDIService.getActivityData(duration);
        return ResponseEntity.ok(activityData);
    }
    @GetMapping("/top-members")
    public ResponseEntity<List<Map<String, Object>>> getTopRDIMembers() {
        List<Publication> publications = publicationService.retrieveAllPublications(); // Fetch all publications

        // Calculate top RDI members based on publications
        Map<Long, Map<String, Object>> rdiMembersMap = new HashMap<>();

        publications.forEach(publication -> {
            publication.getChercheurs().forEach(chercheur -> {
                if (!rdiMembersMap.containsKey(chercheur.getId())) {
                    Map<String, Object> rdiMember = new HashMap<>();
                    rdiMember.put("id", chercheur.getId());
                    rdiMember.put("name", chercheur.getUser().getNameU());
                    rdiMember.put("publicationCount", 0);
                    rdiMember.put("difficultyScore", 0);
                    rdiMembersMap.put(chercheur.getId(), rdiMember);
                }

                Map<String, Object> rdiMember = rdiMembersMap.get(chercheur.getId());
                rdiMember.put("publicationCount", (int) rdiMember.get("publicationCount") + 1);
                rdiMember.put("difficultyScore", (int) rdiMember.get("difficultyScore") + getDifficultyScore(publication.getTypeP()));
            });
        });

        // Convert the map to a list and sort by difficulty score in descending order
        List<Map<String, Object>> rdiMembersList = new ArrayList<>(rdiMembersMap.values());
        rdiMembersList.sort((a, b) -> (int) b.get("difficultyScore") - (int) a.get("difficultyScore"));

        // Return the top 3 RDI members
        List<Map<String, Object>> topRDIMembers = rdiMembersList.subList(0, Math.min(3, rdiMembersList.size()));

        return ResponseEntity.ok(topRDIMembers); // Return the top RDI members as response
    }

    // Method to get the difficulty score based on publication type
    private int getDifficultyScore(TypeP type) {
        switch (type) {
            case A:
                return 7;
            case B:
                return 6;
            case C:
                return 5;
            case D:
                return 4;
            case E:
                return 3;
            case F:
                return 2;
            case G:
                return 1;
            default:
                return 0;
        }
    }



}
