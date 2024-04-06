package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.repositories.EventRepository;
import tn.esprit.esprobackend.entities.Event;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {
    EventRepository eventRepository;
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event retrieveEvent(Long EventId) {
        return eventRepository.findById(EventId).get();
    }

    @Override
    public Event addEvent(Event c) {
        return eventRepository.save(c);
    }

    @Override
    public void removeEvent(Long EventId) {
        eventRepository.deleteById(EventId);
    }

    @Override
    public Event modifyEvent(Event Event) {
        return eventRepository.save(Event);
    }
    @Override
    public List<Event> searchByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Event> getAllEventsSortedByDateAsc() {
        return eventRepository.findAllByOrderByDateAsc();
    }
    public List<Event> getAllEventsSortedByDateDesc(){
        return eventRepository.findAllByOrderByDateDesc();
    }
}
