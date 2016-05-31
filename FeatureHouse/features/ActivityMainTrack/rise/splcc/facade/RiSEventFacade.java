package rise.splcc.facade;

//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
//#endif

public class RiSEventFacade {


	//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
	private ActivityControl activities;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		ActivityRepository activityRepository = ActivityRepositoryBDR.getInstance();
		//#endif
		
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		activities = new ActivityControl(activityRepository);
		//#endif
	}
	
	//ACTIVITY
	//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
	public void insertActivity(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException{
		activities.insert(activity);
	}

	public void removeActivity(int idActivity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		activities.remove(idActivity);  
	}

	public void updateActivity(Activity activity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		activities.update(activity);
	}

	public List<Activity> getActivities() throws RepositoryException{
		return activities.getActivities();
	}

	public Activity searchActivity(int idActivity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		return activities.search(idActivity);
	} 
	
	public int getActivityLastId() throws RepositoryException{
		return activities.getActivityLastId();
	}
	
	public int getActivityIdByName(String activityName) throws RepositoryException{
		return activities.getActivityIdByName(activityName);
	}
	
	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException{
		return activities.getActivitiesByEvent(idEvent);
	}
	
	public float getEventMainTrackValue(int idEvent) throws RepositoryException{
		return activities.getEventMainTrackValue(idEvent);
	}
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException{
		return activities.getActivityMainTrackId(idEvent);
	}
	
	public int getEventbyActivity(int idActivity) throws RepositoryException{
		return activities.getEventbyActivity(idActivity);
	}
	//#endif
}
