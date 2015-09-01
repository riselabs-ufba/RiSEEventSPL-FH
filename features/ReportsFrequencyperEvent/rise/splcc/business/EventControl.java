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
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent (int idEvent) throws RepositoryException{
		return events.getParticipantsPerEvent(idEvent);
	}
	//#endif
	//#if ${ReportsFrequencyperEvent} == "T"
	public void frequencyPerEvent(List<String> ParticipantsPerEvent, Event event) throws DocumentException, IOException{
		event.frequencyPerEvent(ParticipantsPerEvent);
	}
	//#endif
}
