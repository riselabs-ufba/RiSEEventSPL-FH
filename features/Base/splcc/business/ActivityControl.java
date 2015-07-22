//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"

package rise.splcc.business;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import rise.splcc.data.Activity;
import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.ActivityRepository;

import com.lowagie.text.DocumentException;


public class ActivityControl {
	
    private ActivityRepository activities;
	
	public ActivityControl(ActivityRepository repository){
		this.activities = repository;
	}
	
	public void insert(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException{
		if (activity != null) {
            if (!activities.isThere(activity.getIdActivity())) {
                activities.insert(activity);
            } else {
                throw new ActivityAlreadyInsertedException(activity.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(int idActivity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activities.remove(idActivity);
	}
	
	public void update(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activities.update(activity);
	}
	
	public Activity search(int idActivity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		return activities.search(idActivity);
	}

	public boolean isThere(int idActivity) throws RepositoryException {
		return activities.isThere(idActivity);
	}

	public List<Activity> getActivities() throws RepositoryException {
		return activities.getActivities();  
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
	
	//#if ${ReportsFrequencyperActivity} == "T"
	public void frequencyPerActivity(List<String> participantsPerActivity, Activity activity , String eventName) throws DocumentException, IOException{
		activity.frequencyPerActivity(participantsPerActivity, eventName);
	}
	//#endif
	//#if ${ReportsListofAuthors} == "T"
	public void listOfAuthorsPerActivity(Set<String> authorsPerActivity, Activity activity) throws DocumentException, IOException{
		activity.listOfAuthorsPerActivity(authorsPerActivity);
	}
	
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException{
		return activities.getListOfAuthorsPerActivity(idActivity);
	}
	//#endif
}
//#endif