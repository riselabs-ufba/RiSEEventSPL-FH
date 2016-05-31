package rise.splcc.facade;


public class RiSEventFacade {


	//#if ${ReportsFrequencyperActivity} == "T"
	public void frequencyPerActivity(List<String> ParticipantsPerActivity, Activity activity, String eventName) throws DocumentException, IOException{
		activities.frequencyPerActivity(ParticipantsPerActivity, activity, eventName);
	}
	//#endif
	
	
	//#if ${ReportsFrequencyperActivity} == "T"
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		return activityUsers.getParticipantsPerActivity(idActivity);
	}
	//#endif
	
}
