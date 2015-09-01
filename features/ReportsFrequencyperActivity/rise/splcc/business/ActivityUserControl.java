package rise.splcc.business;

public class ActivityUserControl {

	//#if ${ReportsFrequencyperActivity} == "T"
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		return activitiesUsers.getParticipantsPerActivity(idActivity);
	}
	//#endif
}
