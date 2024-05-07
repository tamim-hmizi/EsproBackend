package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.TypeP;
import tn.esprit.esprobackend.repositories.PublicationIRepository;
import tn.esprit.esprobackend.repositories.RDImemberRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements IPublicationService {
    PublicationIRepository PublicationRepository;
    RDImemberRepository RDImemberRepository;

    @Override
    public List<Publication> retrieveAllPublications() {
        return PublicationRepository.findAll();
    }

    @Override
    public Publication retrievePublication(Long Id) {
        return PublicationRepository.findById(Id).get();
    }

    @Override
    public Publication addPublication(Publication c) {
        return PublicationRepository.save(c);
    }

    @Override
    public void removePublication(Long Id) {
        PublicationRepository.deleteById(Id);
    }

    @Override
    public Publication modifyPublication(Publication Publication) {
        return PublicationRepository.save(Publication);
    }

    @Override
    public List<Publication> searchByTitle(String Subject) {
        return null;
    }

    @Override
    public List<Publication> getAllPublicationsByRDIs(RDI RDI) {
        return null;
    }


    @Override
    public List<Publication> getAllPublicationsByuser(RDIMember RDIMember) {
        return null;
    }


    @Override
    public List<Publication> getAllPublicationsSortedByDateAsc() {
        return null;
    }



    public int getDifficultyScore(TypeP type) {
        // Logic to determine the difficulty score based on the type
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

    public Map<String, Integer> calculateActivityByLabels(List<Publication> publications, String duration, List<String> labels) {
        Map<String, Integer> activityByLabel = new HashMap<>();

        for (Publication publication : publications) {
            Date publicationDate = publication.getDateP(); // Retrieve publication date
            int labelIndex = getLabelIndexBasedOnDate(publicationDate, duration); // Get index based on the given duration

            if (labelIndex >= 0) {
                String label = labels.get(labelIndex);
                int currentActivity = activityByLabel.getOrDefault(label, 0);
                int difficulty = getDifficultyScore(publication.getTypeP()); // Calculate difficulty score

                activityByLabel.put(label, currentActivity + difficulty);
            }
        }

        return activityByLabel;
    }

    private int getLabelIndexBasedOnDate(Date publicationDate, String duration) {
        // Logic to return the index based on date and duration
        Calendar cal = Calendar.getInstance();
        cal.setTime(publicationDate);

        if ("1 mois".equals(duration)) {
            return cal.get(Calendar.DAY_OF_MONTH) - 1;
        } else if ("12 mois".equals(duration)) {
            return cal.get(Calendar.MONTH);
        } else if ("5 ans".equals(duration)) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            return 4 - (currentYear - cal.get(Calendar.YEAR));
        }

        return -1; // Default case
    }
    public List<Publication> getCurrentYearPublications() {
        List<Publication> allPublications = retrieveAllPublications(); // Get all publications

        // Filter publications for the current year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Publication> currentYearPublications = allPublications.stream()
                .filter(pub -> {
                    Calendar pubDate = Calendar.getInstance();
                    pubDate.setTime(pub.getDateP());
                    return pubDate.get(Calendar.YEAR) == currentYear;
                })
                .collect(Collectors.toList());

        return currentYearPublications; // Return current year publications
    }
    public List<Map<String, Object>> getTopRDIMembers() {
        return calculateTopRDIMembers(); // Calculate top RDI members

    }

    private List<Map<String, Object>> calculateTopRDIMembers() {
        Map<Long, Map<String, Object>> rdiMembersMap = new HashMap<>();
        List<Publication> publications = getCurrentYearPublications();
        publications.forEach(publication -> {
            publication.getChercheurs().forEach(chercheur -> {
                if (!rdiMembersMap.containsKey(chercheur.getId())) {
                    Map<String, Object> rdiMember = new HashMap<>();
                    rdiMember.put("id", chercheur.getId());
                    rdiMember.put("name", chercheur.getUser().getNom());
                    rdiMember.put("email", chercheur.getUser().getEmail());

                    rdiMember.put("publicationCount", 0);
                    rdiMember.put("difficultyScore", 0);
                    rdiMember.put("user", chercheur.getUser());
                    rdiMember.put("rdi", chercheur.getRDI());

                    rdiMembersMap.put(chercheur.getId(), rdiMember);
                }

                Map<String, Object> rdiMember = rdiMembersMap.get(chercheur.getId());
                rdiMember.put("publicationCount", (int) rdiMember.get("publicationCount") + 1); // Increment publication count
                rdiMember.put("difficultyScore", (int) rdiMember.get("difficultyScore") + getDifficultyScore(publication.getTypeP())); // Increment difficulty score
            });
        });

        // Convert the map to a list and sort by difficulty score (descending)
        List<Map<String, Object>> rdiMembersList = new ArrayList<>(rdiMembersMap.values());
        rdiMembersList.sort((a, b) -> (int) b.get("difficultyScore") - (int) a.get("difficultyScore"));

        // Return the top 3 RDI members
        return rdiMembersList.subList(0, Math.min(3, rdiMembersList.size()));
    }

    // Calculate the difficulty score based on publication type

}

