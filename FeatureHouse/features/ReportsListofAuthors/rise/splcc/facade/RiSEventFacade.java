package rise.splcc.facade;


public class RiSEventFacade {
	
	//#if ${ReportsListofAuthors} == "T"
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException{
		return activities.getListOfAuthorsPerActivity(idActivity);
	}
	
	public void listOfAuthorsPerActivity(Set<String> authorsPerActivity, Activity activity) throws DocumentException, IOException{
		activity.listOfAuthorsPerActivity(authorsPerActivity);
	}
	//#endif
}
