package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;



public interface EventRepository {
	

	public void insert(Event event) throws RepositoryException;

	public void remove(int idEvent) throws EventNotFoundException, RepositoryException;

	public Event search(int idEvent) throws EventNotFoundException, RepositoryException;
	
	public List<Event> getEvents() throws RepositoryException;

	public void update(Event evento) throws EventNotFoundException, RepositoryException;

	public boolean isThere(int idEvent) throws RepositoryException;
	
	public int getEventLastId() throws RepositoryException;
	
	public int getEventIdByName(String eventName) throws RepositoryException;
	
	
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException;
	//#endif
}
