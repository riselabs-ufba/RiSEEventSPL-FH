package rise.splcc.facade;

public class RiSEventFacade {
	
	//#if ${EventImportantDates} == "T"
	public void generateImportantDates(String abstractDate, String fullPaperDate, String notificationDate, Event event) throws DocumentException, IOException{
		events.generateImportantDates(abstractDate, fullPaperDate, notificationDate, event);
	}
	
}
