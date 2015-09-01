package rise.splcc.repository;

public interface ActivityRepository {
	
	//#if ${ReportsListofAuthors} == "T"
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException;
	//#endif
}
