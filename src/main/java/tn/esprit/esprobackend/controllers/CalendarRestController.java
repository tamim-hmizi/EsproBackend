package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Calendar;
import tn.esprit.esprobackend.services.ICalendarService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/calendar")
public class CalendarRestController {
    ICalendarService calendarService;
    // http://localhost:8089/esprobackend/calendar/retrieve-all-calendars
    @GetMapping("/retrieve-all-calendars")
    public List<Calendar> getCalendars() {
        List<Calendar> listCalendars = calendarService.retrieveAllCalendars();
        return listCalendars;
    }
    // http://localhost:8089/esprobackend/calendar/retrieve-calendar/8
    @GetMapping("/retrieve-calendar/{calendar-id}")
    public Calendar retrieveCalendar(@PathVariable("calendar-id") Long chId) {
        Calendar calendar = calendarService.retrieveCalendar(chId);
        return calendar;
    }
    // http://localhost:8089/esprobackend/calendar/add-calendar
    @PostMapping("/add-calendar")
    public Calendar addCalendar(@RequestBody Calendar c) {
        Calendar calendar = calendarService.addCalendar(c);
        return calendar;
    }
    // http://localhost:8089/esprobackend/calendar/remove-calendar/{calendar-id}
    @DeleteMapping("/remove-calendar/{calendar-id}")
    public void removeCalendar(@PathVariable("calendar-id") Long chId) {
        calendarService.removeCalendar(chId);
    }
    // http://localhost:8089/esprobackend/calendar/modify-calendar
    @PutMapping("/modify-calendar")
    public Calendar modifyCalendar(@RequestBody Calendar c) {
        Calendar calendar = calendarService.modifyCalendar(c);
        return calendar;
    }
}
