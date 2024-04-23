package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.services.IEventService;
import tn.esprit.esprobackend.entities.Event;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Event")
public class EventRestController {
    IEventService eventService;
    @GetMapping("/retrieve-all-Events")
    public List<Event> getEvents() {
        List<Event> listEvents = eventService.retrieveAllEvents();
        return listEvents;
    }

    @GetMapping("/retrieve-Event/{Event-id}")
    public Event retrieveEvent(@PathVariable("Event-id") Long evId) {
        Event Event = eventService.retrieveEvent(evId);
        return Event;
    }
    @PostMapping("/add-Event")
    public Event addEvent(@RequestBody Event c) {
        Event Event = eventService.addEvent(c);
        return Event;
    }

    @DeleteMapping("/remove-Event/{Event-id}")
    public void removeEvent(@PathVariable("Event-id") Long evId) {
        eventService.removeEvent(evId);
    }

    @PutMapping("/modify-Event")
    public Event modifyEvent(@RequestBody Event c) {
        Event Event = eventService.modifyEvent(c);
        return Event;
    }
}
