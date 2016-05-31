package rise.splcc.facade;


public class RiSEventFacade {
	
	//#if ${EventProgram} == "T"
	public void generateProgram (List<Activity> activities, Event event) throws DocumentException, IOException{
		events.generateProgram(activities, event);
	}
	//#endif
	
}
