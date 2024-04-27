package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.eventScraps;
import tn.esprit.esprobackend.repositories.EventScrapsRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class EventScrapsImpl implements IEventScrapsService {
    EventScrapsRepository eventScrapsRepository;
    @Override
    public void addEvent(eventScraps event) {
        eventScrapsRepository.save(event);
    }

    @Override
    public List<eventScraps> getAllEvents() {
        return eventScrapsRepository.findAll();
    }
}
