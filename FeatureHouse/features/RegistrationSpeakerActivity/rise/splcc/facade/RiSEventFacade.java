package rise.splcc.facade;

//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.business.ActivitySpeakerControl;
//#endif
//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.data.ActivitySpeaker;
//#endif
//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.exception.ActivitySpeakerAlreadyInsertedException;
import rise.splcc.exception.ActivitySpeakerNotFoundException;
//#endif
//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.repository.ActivitySpeakerRepository;
import rise.splcc.repository.ActivitySpeakerRepositoryBDR;
//#endif

public class RiSEventFacade {

	//#if ${RegistrationSpeakerActivity} == "T"
	private ActivitySpeakerControl activitySpeakers;
	//#endif
	
	public void init(){
		original();

		//#if ${RegistrationSpeakerActivity} == "T"
		ActivitySpeakerRepository activitySpeakerRepository = ActivitySpeakerRepositoryBDR.getInstance();
		//#endif

		//#if ${RegistrationSpeakerActivity} == "T"
		activitySpeakers = new ActivitySpeakerControl(activitySpeakerRepository);
		//#endif
	}
	
	
	//ACTIVITYSPEAKER
	//#if ${RegistrationSpeakerActivity} == "T"
	public void insertActivitySpeaker(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException{
		activitySpeakers.insert(activitySpeaker);
	}

	public void removeActivitySpeaker(ActivitySpeaker activitySpeaker) throws ActivitySpeakerNotFoundException, RepositoryException, ActivitySpeakerAlreadyInsertedException{
		activitySpeakers.remove(activitySpeaker);  
	}
	
	public List<ActivitySpeaker> getActivitiesSpeakers() throws RepositoryException{
		return activitySpeakers.getActivitiesSpeakers();
	}
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException{
		return activitySpeakers.getActivitiesById(idActivity);
	}
	//#endif
	
}
