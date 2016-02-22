package rise.splcc.business;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.EventRepository;


public class EventControl {
	
    private EventRepository events;
	
	public EventControl(EventRepository repository){
		this.events = repository;
	}
	
	public void insert(Event event) throws EventAlreadyInsertedException, RepositoryException{
		if (event != null) {
            if (!events.isThere(event.getIdEvent())) {
                events.insert(event);
            } else {
                throw new EventAlreadyInsertedException(event.getIdEvent());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idEvent) throws EventAlreadyInsertedException, RepositoryException, EventNotFoundException{
		events.remove(idEvent);
	}
	
	public void update(Event event) throws EventAlreadyInsertedException, RepositoryException, EventNotFoundException{
		events.update(event);
	}
	
	public Event search(int idEvent) throws EventAlreadyInsertedException, RepositoryException, EventNotFoundException{
		return events.search(idEvent);
	}

	public boolean isThere(int idEvent) throws RepositoryException {
		return events.isThere(idEvent);
	}

	public List<Event> getEvents() throws RepositoryException {
		return events.getEvents();  
	}
	
	public int getEventLastId() throws RepositoryException{
		return events.getEventLastId();
	}
	
	public int getEventIdByName(String eventName) throws RepositoryException{
		return events.getEventIdByName(eventName);
	}
	
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent (int idEvent) throws RepositoryException{
		return events.getParticipantsPerEvent(idEvent);
	}
	//#endif
	//#if ${EventProgram} == "T"
	public void generateProgram(List<Activity> activities, Event event) throws DocumentException, IOException{
		event.generateProgram(activities);
	}
	//#endif
	//#if ${EventImportantDates} == "T"
	public void generateImportantDates (String abstractDate, String fullPaperDate, String notificationDate, Event event) throws DocumentException, IOException{
		event.generateImportantDates(abstractDate, fullPaperDate, notificationDate);
	}
	//#endif
	//#if ${ReportsFrequencyperEvent} == "T"
	public void frequencyPerEvent(List<String> ParticipantsPerEvent, Event event) throws DocumentException, IOException{
		event.frequencyPerEvent(ParticipantsPerEvent);
	}
	//#endif
}
