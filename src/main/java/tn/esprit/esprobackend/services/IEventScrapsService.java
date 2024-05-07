package tn.esprit.esprobackend.services;
import  tn.esprit.esprobackend.entities.eventScraps;

import java.util.List;

public interface IEventScrapsService {
    public void addEvent(eventScraps event);
    public List<eventScraps> getAllEvents();
}
