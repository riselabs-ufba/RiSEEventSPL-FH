package rise.splcc.facade;

public class RiSEventFacade {

	//#if ${RegistrationUserActivity} == "T"
	private ActivityUserControl activityUsers;
	//#endif
	
	
	public void init(){
		
		original();
		
		//#if ${RegistrationUserActivity} == "T"
		ActivityUserRepository activityUserRepository = ActivityUserRepositoryBDR.getInstance();
		//#endif
		
		//#if ${RegistrationUserActivity} == "T"
		activityUsers = new ActivityUserControl(activityUserRepository);
		//#endif
	}
	
	
	//ACTIVITYUSER
	//#if ${RegistrationUserActivity} == "T"
	public void insertActivityUser(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException{
		activityUsers.insert(activityUser);
	}

	public void removeActivityUser(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException, ActivityUserAlreadyInsertedException{
		activityUsers.remove(activityUser);  
	}
	
	public List<ActivityUser> getActivitiesUsers() throws RepositoryException{
		return activityUsers.getActivitiesUsers();
	}
	
	public List<ActivityUser> getActivitiesUsersById(int idActivity) throws RepositoryException{
		return activityUsers.getActivitiesById(idActivity);
	}
	//#endif
}
