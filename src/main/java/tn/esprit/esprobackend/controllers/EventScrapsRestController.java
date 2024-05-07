package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.eventScraps;
import tn.esprit.esprobackend.services.IEventScrapsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/EventScraps")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventScrapsRestController {
    IEventScrapsService eventScrapsService;
    @GetMapping("/retrieve-all-Events")
    public List<eventScraps> getEvents() {
        return eventScrapsService.getAllEvents();
    }
    @PostMapping("/add-event")
    public void addEvent(@RequestBody eventScraps eventScraps)
    {
        eventScrapsService.addEvent(eventScraps);
    }
}
