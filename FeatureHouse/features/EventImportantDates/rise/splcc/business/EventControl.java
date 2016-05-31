package rise.splcc.business;

public class EventControl {
	
	//#if ${EventImportantDates} == "T"
	public void generateImportantDates (String abstractDate, String fullPaperDate, String notificationDate, Event event) throws DocumentException, IOException{
		event.generateImportantDates(abstractDate, fullPaperDate, notificationDate);
	}
	//#endif
}
