package rise.splcc.business;

public class SubmissionControl {
	
  
	//#if ${SubmissionCompleta} == "T"
	public void inserAttachmanet(File attachment, int idActivity) throws SubmissionAlreadyInsertedException, RepositoryException{
		submissions.insert(attachment, idActivity);
	}
	//#endif
	
}
