//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.SubmissionUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SubmissionUserNotFoundException;

public interface SubmissionUserRepository {
	
	public void insert(SubmissionUser submissionUser) throws RepositoryException;

	public void remove(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;

	public SubmissionUser search(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;
	
	public List<SubmissionUser> getSubmissionUsers() throws RepositoryException;

	public void update(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;

	public boolean isThere(SubmissionUser submissionUser) throws RepositoryException;
	
	
	
}
//#endif