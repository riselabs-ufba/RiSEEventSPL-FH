package rise.splcc.facade;

//#if ${Organizer} == "T"
import rise.splcc.business.OrganizerControl;
//#endif
//#if ${Organizer} == "T"
import rise.splcc.data.Organizer;
//#endif
//#if ${Organizer} == "T"
import rise.splcc.exception.OrganizerAlreadyInsertedException;
import rise.splcc.exception.OrganizerNotFoundException;
//#endif
//#if ${Organizer} == "T"
import rise.splcc.repository.OrganizerRepository;
import rise.splcc.repository.OrganizerRepositoryBDR;
//#endif


public class RiSEventFacade {

	//#if ${Organizer} == "T"
	private OrganizerControl organizers;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${Organizer} == "T"
		OrganizerRepository organizerRepository = OrganizerRepositoryBDR.getInstance();
		//#endif
		
		//#if ${Organizer} == "T"
		organizers = new OrganizerControl(organizerRepository);
		//#endif
	}
	
	//ORGANIZER
	//#if ${Organizer} == "T"
	public void insertOrganizer(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException{
		organizers.insert(organizer);
	}

	public void removeOrganizer(int idUser) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		organizers.remove(idUser);  
	}

	public void updateOrganizer(Organizer organizer) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		organizers.update(organizer);
	}

	public List<Organizer> getOrganizers() throws RepositoryException{
		return organizers.getOrganizers();
	}

	public Organizer searchOrganizer(int idUser) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		return organizers.search(idUser);
	}
	public boolean isThereOrganizer(int idUser) throws RepositoryException{
		return organizers.isThere(idUser);
	}
	//#endif
	
}
