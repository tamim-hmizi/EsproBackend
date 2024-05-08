package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.esprobackend.entities.eventScraps;

public interface EventScrapsRepository extends JpaRepository<eventScraps,Long> {
}
