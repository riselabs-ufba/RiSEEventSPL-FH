//#if ${InsertAuthors} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.SubmissionAuthor;
import rise.splcc.data.SubmissionUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SubmissionAuthorNotFoundException;
import rise.splcc.exception.SubmissionUserNotFoundException;

public interface SubmissionAuthorRepository {
	public void insert(SubmissionAuthor submissionAuthor) throws RepositoryException;

	public void remove(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public SubmissionAuthor search(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;
	
	public List<SubmissionAuthor> getSubmissionAuthors() throws RepositoryException;

	public void update(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public boolean isThere(SubmissionAuthor submissionAuthor) throws RepositoryException;
}
//#endif