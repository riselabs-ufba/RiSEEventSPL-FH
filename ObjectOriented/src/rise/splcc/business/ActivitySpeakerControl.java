//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.business;

import java.util.List;

import rise.splcc.data.Activity;
import rise.splcc.data.ActivitySpeaker;
import rise.splcc.exception.ActivitySpeakerAlreadyInsertedException;
import rise.splcc.exception.ActivitySpeakerNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.ActivitySpeakerRepository;


public class ActivitySpeakerControl {
	
    private ActivitySpeakerRepository activitiesSpeakers;
	
	public ActivitySpeakerControl(ActivitySpeakerRepository repository){
		this.activitiesSpeakers = repository;
	}
	
	public void insert(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException{
		if (activitySpeaker != null) {
            if (activitiesSpeakers.isThere(activitySpeaker) == false) {
            	activitiesSpeakers.insert(activitySpeaker);
            } else {
                throw new ActivitySpeakerAlreadyInsertedException(activitySpeaker.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{		
		activitiesSpeakers.remove(activitySpeaker);
	}
	
	public void update(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{
		activitiesSpeakers.update(activitySpeaker);
	}
	
	public ActivitySpeaker search(int idActivity) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{
		return activitiesSpeakers.search(idActivity);
	}

	public boolean isThere(ActivitySpeaker activitySpeaker) throws RepositoryException {
		return activitiesSpeakers.isThere(activitySpeaker);
	}

	public List<ActivitySpeaker> getActivitiesSpeakers() throws RepositoryException {
		return activitiesSpeakers.getActivitiesSpeakers();  
	}
	
	public int getActivitySpeakerLastId() throws RepositoryException{
		return activitiesSpeakers.getActivitySpeakerLastId();
	}
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException{
		return activitiesSpeakers.getActivitiesById(idActivity);
	}
}
//#endif