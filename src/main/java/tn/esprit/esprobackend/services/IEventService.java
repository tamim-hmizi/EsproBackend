package tn.esprit.esprobackend.services;
import tn.esprit.esprobackend.entities.Event;
import java.util.List;

public interface IEventService {
    public List<Event> retrieveAllEvents();
    public Event retrieveEvent(Long EventId);
    public Event addEvent(Event c);
    public void removeEvent(Long EventId);
    public Event modifyEvent(Event Event);
    public List<Event> searchByTitle(String title);
    public List<Event> getAllEventsSortedByDateAsc();
    public List<Event> getAllEventsSortedByDateDesc();
}
