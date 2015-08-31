package rise.splcc.facade;

//#if ${RegistrationOrganizerActivity} == "T"
import rise.splcc.business.ActivityOrganizerControl;
//#endif
//#if ${RegistrationOrganizerActivity} == "T"
import rise.splcc.data.ActivityOrganizer;
//#endif
//#if ${RegistrationOrganizerActivity} == "T"
import rise.splcc.exception.ActivityOrganizerAlreadyInsertedException;
import rise.splcc.exception.ActivityOrganizerNotFoundException;
//#endif


public class RiSEventFacade {

	//#if ${RegistrationOrganizerActivity} == "T"
	private ActivityOrganizerControl activityOrganizers;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${RegistrationOrganizerActivity} == "T"
		ActivityOrganizerRepository activityOrganizerRepository = ActivityOrganizerRepositoryBDR.getInstance();
		//#endif

		//#if ${RegistrationOrganizerActivity} == "T"
		activityOrganizers = new ActivityOrganizerControl(activityOrganizerRepository);
		//#endif
	}
	
	//ACTIVITYORGANIZER
	//#if ${RegistrationOrganizerActivity} == "T"
	public void insertActivityOrganizer(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException{
		activityOrganizers.insert(activityOrganizer);
	}

	public void removeActivityOrganizer(ActivityOrganizer activityOrganizer) throws ActivityOrganizerNotFoundException, RepositoryException, ActivityOrganizerAlreadyInsertedException{
		activityOrganizers.remove(activityOrganizer);  
	}

	public List<ActivityOrganizer> getActivitiesOrganizers() throws RepositoryException{
		return activityOrganizers.getActivitiesOrganizers();
	}

	public List<ActivityOrganizer> getActivitiesOrganizersById(int idActivity) throws RepositoryException{
		return activityOrganizers.getActivitiesById(idActivity);
	}
	//#endif
	
}
