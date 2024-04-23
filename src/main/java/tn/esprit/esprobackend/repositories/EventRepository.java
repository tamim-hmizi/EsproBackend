package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.esprobackend.entities.Event;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findAllByOrderByDateAsc();
    List <Event> findAllByOrderByDateDesc();
}
