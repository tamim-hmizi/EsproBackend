package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.ResearchAxis;
import tn.esprit.esprobackend.repositories.PublicationIRepository;
import tn.esprit.esprobackend.repositories.RDIRepository;
import tn.esprit.esprobackend.repositories.RDImemberRepository;
import tn.esprit.esprobackend.repositories.ResearchAxisIRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RDIServiceImpl implements IRDIService {
    RDIRepository RDIRepositorys;
    ResearchAxisIRepository ResearchAxisRepository;
    PublicationIRepository PublicationRepository;
    RDImemberRepository RDIMemberRepository;



    @Override
    public List<RDI> retrieveAllRDIs() {
        return RDIRepositorys.findAll();
    }

    @Override
    public RDI retrieveRDI(Long RDIId) {
        return RDIRepositorys.findById(RDIId).get();
    }

    @Override
    public RDI addRDI(RDI c) {
        return RDIRepositorys.save(c);
    }

    @Override
    public void removeRDI(Long RDIId) {
        RDIRepositorys.deleteById(RDIId);

    }

    @Override
    public RDI modifyRDI(RDI RDI) {
        return  RDIRepositorys.save(RDI);
    }

    @Override
    public List<RDI> searchByTitle(String theme) {
        return null;
    }




    @Override
    public List<RDI> searchByTypeR(String TypeR) {
        return null;
    }

    @Override
    public List<RDI> searchByPublication(Publication Publication) {
        return null;
    }

    @Override
    public List<RDI> searchByResearchAxis(ResearchAxis ResearchAxis) {
        return null;
    }

    @Override
    public List<RDI> searchByPublication(RDIMember RDIMember) {
        return null;
    }

    @Override
    public List<RDI> searchByKeyWords(String KeyWord) {
        return null;
    }

    @Override
    public List<RDI> getAllRDIsSortedByDateAsc() {
        return null;
    }

    @Override

    public void assignResearchAxisToRDI(Long ResearchAxisId, Long RDIId) {
        RDI RDI = RDIRepositorys.findById(RDIId).get();
        ResearchAxis ResearchAxis = ResearchAxisRepository.findById(ResearchAxisId).get();
        Set<ResearchAxis> ResearchAxislist =RDI.getResearchAxis();
        // on set le fils dans le parent :
        ResearchAxislist.add(ResearchAxis);
        RDI.setResearchAxis(ResearchAxislist);
        RDIRepositorys.save(RDI);
    }
    public void assignRDIMemberToRDI( Long RDIId, RDIMember Member) {
        RDI RDI = RDIRepositorys.findById(RDIId).get();
        Member.setRDI(RDI);
        RDIMemberRepository.save(Member);
        RDI.getRDIMembers().add(Member);



        RDIRepositorys.save(RDI);
    }

    public boolean existsByTheme( String theme) {

      return  RDIRepositorys.existsByTheme(theme);
    }
    @Override

    public Map<String, List<Integer>> getActivityData(String duration) {
        // This is where you'd implement your logic to get the data based on the duration
        // Example data structure
        Map<String, List<Integer>> activityData = new HashMap<>();

        // Example: populate the map with data
        // You'll need to implement your logic for fetching and processing the data here

        return activityData;
    }

    public List<String> getWordListFromRDI(RDI rdi) {
        List<String> words = new ArrayList<>();
        String delimiters = "[,/+''``_]";
        if (rdi != null) {
            // Ajoute des mots à partir des champs RDI, avec vérification de null avant de faire split
            words.addAll(
                    Optional.ofNullable(rdi.getTheme())
                            .map(theme -> theme.split(delimiters))
                            .map(Arrays::asList)
                            .orElse(Collections.emptyList())
            );

            words.addAll(
                    Optional.ofNullable(rdi.getKeywords())
                            .map(keywords -> keywords.split(delimiters))
                            .map(Arrays::asList)
                            .orElse(Collections.emptyList())
            );

            words.addAll(
                    Optional.ofNullable(rdi.getTypeR())
                            .map(typeR -> typeR.name().split(delimiters))
                            .map(Arrays::asList)
                            .orElse(Collections.emptyList())
            );

            // Ajoute des mots à partir des ResearchAxis, avec vérification de null
            rdi.getResearchAxis().forEach(ra -> {
                words.addAll(
                        Optional.ofNullable(ra.getSubjectRA())
                                .map(subject -> subject.split(delimiters))
                                .map(Arrays::asList)
                                .orElse(Collections.emptyList())
                );

                words.addAll(
                        Optional.ofNullable(ra.getDescriptionRA())
                                .map(description -> description.split(delimiters))
                                .map(Arrays::asList)
                                .orElse(Collections.emptyList())
                );
            });

            // Ajoute des mots à partir des publications
            rdi.getRDIMembers().forEach(member -> {
                member.getPublications().forEach(publication -> {
                    words.addAll(
                            Optional.ofNullable(publication.getDescriptionP())
                                    .map(description -> description.split(delimiters))
                                    .map(Arrays::asList)
                                    .orElse(Collections.emptyList())
                    );

                    words.addAll(
                            Optional.ofNullable(publication.getSubjectP())
                                    .map(subject -> subject.split(delimiters))
                                    .map(Arrays::asList)
                                    .orElse(Collections.emptyList())
                    );
                });
            });
        }

      return words.stream().distinct().collect(Collectors.toList()); /// S'assure qu'il n'y a pas de doublons
      }
    @Override

    public List<RDI> getMostSimilarRDIs(Long targetRDIId) {
        RDI targetRDI = RDIRepositorys.findById(targetRDIId).get();; // Retrieve the target RDI
        if (targetRDI == null) {
            return Collections.emptyList(); // Return empty list if no target RDI found
        }

        List<String> targetWords = getWordListFromRDI(targetRDI); // Get words from the target RDI
        Map<RDI, Integer> similarityMap = new HashMap<>(); // Map to store similarity scores

        retrieveAllRDIs().forEach(rdi -> {
            if (!rdi.getId().equals(targetRDIId)) { // Don't compare with itself
                List<String> rdiWords = getWordListFromRDI(rdi);
                int commonWords = (int) rdiWords.stream().filter(targetWords::contains).count(); // Count common words
                similarityMap.put(rdi, commonWords); // Store similarity score
            }
        });

        // Sort RDIs based on similarity score in descending order
        return similarityMap.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}


