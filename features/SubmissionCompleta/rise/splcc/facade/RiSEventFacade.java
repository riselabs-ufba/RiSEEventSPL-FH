package rise.splcc.facade;

public class RiSEventFacade {
	
	//#if ${SubmissionCompleta} == "T"
	public void insertAttachment(File attachment, int idActivity) throws RepositoryException, SubmissionAlreadyInsertedException{
		this.submissions.inserAttachmanet(attachment, idActivity);
	}
	//#endif
}
