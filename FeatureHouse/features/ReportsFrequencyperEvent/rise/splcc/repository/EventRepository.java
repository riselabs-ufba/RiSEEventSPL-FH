package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface EventRepository {

	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException;
	//#endif
}
