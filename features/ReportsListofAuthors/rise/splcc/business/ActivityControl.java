package rise.splcc.business;

public class ActivityControl {
	
	//#if ${ReportsListofAuthors} == "T"
	public void listOfAuthorsPerActivity(Set<String> authorsPerActivity, Activity activity) throws DocumentException, IOException{
		activity.listOfAuthorsPerActivity(authorsPerActivity);
	}
	
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException{
		return activities.getListOfAuthorsPerActivity(idActivity);
	}
	//#endif
}
