package rise.splcc.facade;


//#if ${Speaker} == "T"
import rise.splcc.business.SpeakerControl;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.data.Speaker;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.exception.SpeakerAlreadyInsertedException;
import rise.splcc.exception.SpeakerNotFoundException;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.repository.SpeakerRepository;
import rise.splcc.repository.SpeakerRepositoryBDR;
//#endif


public class RiSEventFacade {


	//#if ${Speaker} == "T"
	private SpeakerControl speakers;
	//#endif
	
	public RiSEventFacade(){
//		init();
	}
	
	public void init(){
		original();

		//#if ${Speaker} == "T"
		SpeakerRepository speakerRepository = SpeakerRepositoryBDR.getInstance();
		//#endif
		
		//#if ${Speaker} == "T"
		speakers = new SpeakerControl(speakerRepository);
		//#endif
		
	}

	
	//SPEAKER
	//#if ${Speaker} == "T"
	public void insertSpeaker(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException{
		speakers.insert(speaker);
	}

	public void removeSpeaker(int idUser) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		speakers.remove(idUser);  
	}

	public void updateSpeaker(Speaker speaker) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		speakers.update(speaker);
	}

	public List<Speaker> getSpeakers() throws RepositoryException{
		return speakers.getSpeakers();
	}

	public Speaker searchSpeaker(int idUser) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		return speakers.search(idUser);
	} 
	
	public boolean isThereSpeaker(int idUser) throws RepositoryException{
		return speakers.isThere(idUser);
	}
	//#endif
	
}

