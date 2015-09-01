package rise.splcc.repository;

public interface ActivityUserRepository {
	
	//#if ${ReportsFrequencyperActivity} == "T"
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException;
	//#endif
}
