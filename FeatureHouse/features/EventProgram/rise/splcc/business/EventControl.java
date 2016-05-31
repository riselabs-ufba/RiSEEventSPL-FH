package rise.splcc.business;

public class EventControl {
	
	//#if ${EventProgram} == "T"
		public void generateProgram(List<Activity> activities, Event event) throws DocumentException, IOException{
			event.generateProgram(activities);
		}
		//#endif
}
