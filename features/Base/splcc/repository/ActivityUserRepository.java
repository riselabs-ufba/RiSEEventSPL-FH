//#if ${RegistrationUserActivity} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.ActivityUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ActivityUserNotFoundException;

public interface ActivityUserRepository {
	
	public void insert(ActivityUser activityUser) throws RepositoryException;

	public void remove(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException;

	public ActivityUser search(int idUser) throws ActivityUserNotFoundException, RepositoryException;
	
	public List<ActivityUser> getActivitiesUsers() throws RepositoryException;

	public void update(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException;

	public boolean isThere(ActivityUser activityUser) throws RepositoryException;
	
	public int getActivityUserLastId() throws RepositoryException;
	
	public List<ActivityUser> getActivitiesById(int idActivity) throws RepositoryException;
	//#if ${ReportsFrequencyperActivity} == "T"
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException;
	//#endif
}
//#endif