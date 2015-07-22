//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.ActivitySpeaker;
import rise.splcc.exception.ActivitySpeakerNotFoundException;
import rise.splcc.exception.RepositoryException;



public interface ActivitySpeakerRepository {
	
	
	public void insert(ActivitySpeaker activityspeaker) throws RepositoryException;

	public void remove(ActivitySpeaker activityspeaker) throws ActivitySpeakerNotFoundException, RepositoryException;

	public ActivitySpeaker search(int idActivity) throws ActivitySpeakerNotFoundException, RepositoryException;
	
	public List<ActivitySpeaker> getActivitiesSpeakers() throws RepositoryException;

	public void update(ActivitySpeaker activityo) throws ActivitySpeakerNotFoundException, RepositoryException;

	public boolean isThere(ActivitySpeaker activitySpeaker) throws RepositoryException;
	
	public int getActivitySpeakerLastId() throws RepositoryException;
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException;

}
//#endif