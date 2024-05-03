package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Calendar;
import tn.esprit.esprobackend.repositories.CalendarRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CalendarServiceImpl implements ICalendarService {
    CalendarRepository calendarRepository;
    public List<Calendar> retrieveAllCalendars() {
        return calendarRepository.findAll();
    }
    public Calendar retrieveCalendar(Long id) {
        return calendarRepository.findById(id).get();
    }
    public Calendar addCalendar(Calendar c) {
        return calendarRepository.save(c);
    }
    public void removeCalendar(Long id) {
        calendarRepository.deleteById(id);
    }
    public Calendar modifyCalendar(Calendar bloc) {
        return calendarRepository.save(bloc);
    }
}

