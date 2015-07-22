//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.business;

import java.io.File;
import java.util.List;

import rise.splcc.data.Submission;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SubmissionAlreadyInsertedException;
import rise.splcc.exception.SubmissionNotFoundException;
import rise.splcc.repository.SubmissionRepository;


public class SubmissionControl {
	
    private SubmissionRepository submissions;
	
	public SubmissionControl(SubmissionRepository repository){
		this.submissions = repository;
	}

	
	public void insert(Submission submission) throws SubmissionAlreadyInsertedException, RepositoryException{
		if (submission != null) {
			if (!submissions.isThere(submission.getIdSubmission())) 
				submissions.insert(submission);
			else
				throw new SubmissionAlreadyInsertedException(submission.getIdSubmission());
		} else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idSubmission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		submissions.remove(idSubmission);
	}
	
	public void update(Submission submission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		submissions.update(submission);
	}
	
	
	public List<Submission> getSubmissions() throws RepositoryException {
		return submissions.getSubmissions();  
	}
	
	public int getSubmissionLastId() throws RepositoryException{
		return submissions.getSubmissionLastId();
	}


	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException{
		return submissions.getSubmissionIdByTitle(submissionTitle);
	}

	public Submission search(int idSubmission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		return submissions.search(idSubmission);
	}
	//#if ${SubmissionCompleta} == "T"
	public void inserAttachmanet(File attachment, int idActivity) throws SubmissionAlreadyInsertedException, RepositoryException{
		submissions.insert(attachment, idActivity);
	}
	//#endif
	public void pdfRecover(int idSubmission) throws RepositoryException {
		submissions.pdfRecover(idSubmission);
	}
	
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException{
		return submissions.getSubmissionsByUser(idUser);
	}
}
//#endif