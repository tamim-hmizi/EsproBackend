package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Calendar;
import java.util.List;
public interface ICalendarService {
    public List<Calendar> retrieveAllCalendars();
    public Calendar retrieveCalendar(Long CalendarId);
    public Calendar addCalendar(Calendar c);
    public void removeCalendar(Long CalendarId);
    public Calendar modifyCalendar(Calendar Calendar);
}
