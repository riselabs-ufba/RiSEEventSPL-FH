package rise.splcc.facade;


public class RiSEventFacade {

	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException{
		return events.getParticipantsPerEvent(idEvent);
	}
	//#endif
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public void frequencyPerEvent(List<String> ParticipantsPerEvent, Event event) throws DocumentException, IOException{
		events.frequencyPerEvent(ParticipantsPerEvent, event);
	}
	//#endif
}
