package rise.splcc.facade;
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.business.SubmissionControl;
import rise.splcc.business.SubmissionUserControl;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.data.Submission;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.data.SubmissionUser;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.exception.SubmissionAlreadyInsertedException;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.exception.SubmissionNotFoundException;
import rise.splcc.exception.SubmissionUserAlreadyInsertedException;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.repository.SubmissionRepository;
import rise.splcc.repository.SubmissionRepositoryBDR;
import rise.splcc.repository.SubmissionUserRepository;
import rise.splcc.repository.SubmissionUserRepositoryBDR;
//#endif


public class RiSEventFacade {

	//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
	private SubmissionControl submissions;
	//#endif
	//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
	private SubmissionUserControl submissionUsers;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		SubmissionRepository submissionRepository = SubmissionRepositoryBDR.getInstance();
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		SubmissionUserRepository submissionUserRepository = SubmissionUserRepositoryBDR.getInstance();
		//#endif
		
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		submissions = new SubmissionControl(submissionRepository);
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		submissionUsers = new SubmissionUserControl(submissionUserRepository);
		//#endif
	}
	
	//SUBMISSION
	//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
	public void insertSubmission(Submission submission) throws RepositoryException, SubmissionAlreadyInsertedException{
		this.submissions.insert(submission);
	}
	
	public List<Submission> getSubmissions() throws RepositoryException{
		return submissions.getSubmissions();
	}
	
	public int getSubmissionLastId() throws RepositoryException{
		return submissions.getSubmissionLastId();
	}
	
	public void removeSubmission(int idSubmission) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		submissions.remove(idSubmission);  
	}
	
	public void updateSubmission(Submission submission) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		submissions.update(submission);
	}
	
	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException{
		return submissions.getSubmissionIdByTitle(submissionTitle);
	}
	
	public Submission searchSubmission(int idSubmission) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		return submissions.search(idSubmission);
	}
	
	//#endif
	public void pdfRecovey(int idSubmission) throws RepositoryException{
		this.submissions.pdfRecover(idSubmission);
	}
	
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException{
		return submissions.getSubmissionsByUser(idUser);
	}
	
	//SUBMISSIONUSER
	
	public void insertSubmissionUser(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException{
		submissionUsers.insert(submissionUser);
	}
	
	public List<SubmissionUser> getSubmissionUsers() throws RepositoryException{
		return submissionUsers.getSubmissionUsers();
	}
	//#endif
	
}
