//#if ${RegistrationOrganizerActivity} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.ActivityOrganizer;
import rise.splcc.exception.ActivityOrganizerNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface ActivityOrganizerRepository {

	public void insert(ActivityOrganizer activityOrganzier) throws RepositoryException;

	public void remove(ActivityOrganizer activityOrganzier) throws ActivityOrganizerNotFoundException, RepositoryException;

	public ActivityOrganizer search(int idActivity) throws ActivityOrganizerNotFoundException, RepositoryException;
	
	public List<ActivityOrganizer> getActivitiesOrganizers() throws RepositoryException;

	public void update(ActivityOrganizer activityOrganzier) throws ActivityOrganizerNotFoundException, RepositoryException;

	public boolean isThere(ActivityOrganizer activityOrganzier) throws RepositoryException;
	
	public int getActivityOrganizerLastId() throws RepositoryException;
	
	public List<ActivityOrganizer> getActivitiesById(int idActivity) throws RepositoryException;

}
//#endif