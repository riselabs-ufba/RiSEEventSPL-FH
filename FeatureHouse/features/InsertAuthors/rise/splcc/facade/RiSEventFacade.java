package rise.splcc.facade;

//#if ${InsertAuthors} == "T"
import rise.splcc.business.AuthorControl;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.business.SubmissionAuthorControl;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.data.Author;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.data.SubmissionAuthor;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.AuthorAlreadyInsertedException;
import rise.splcc.exception.AuthorNotFoundException;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.SubmissionAuthorAlreadyInsertedException;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.repository.AuthorRepository;
import rise.splcc.repository.AuthorRepositoryBDR;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.repository.SubmissionAuthorRepository;
import rise.splcc.repository.SubmissionAuthorRepositoryBDR;
//#endif

public class RiSEventFacade {


	//#if ${InsertAuthors} == "T"
	private SubmissionAuthorControl submissionAuthors;
	//#endif
	//#if ${InsertAuthors} == "T"
	private AuthorControl authors;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${InsertAuthors} == "T"
		SubmissionAuthorRepository submissionAuthorRepository = SubmissionAuthorRepositoryBDR.getInstance();
		//#endif
		//#if ${InsertAuthors} == "T"
		AuthorRepository authorRepository = AuthorRepositoryBDR.getInstance();
		//#endif
		
		//#if ${InsertAuthors} == "T"
		submissionAuthors = new SubmissionAuthorControl(submissionAuthorRepository);
		//#endif
		//#if ${InsertAuthors} == "T"
		authors = new AuthorControl(authorRepository);
		//#endif
	}
	
	//SUBMISSIONAUTHOR
	//#if ${InsertAuthors} == "T"
	public void insertSubmissionAuthor(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException{
		submissionAuthors.insert(submissionAuthor);
	}
	
	public List<SubmissionAuthor> getSubmissionAuthors() throws RepositoryException{
		return submissionAuthors.getSubmissionAuthors();
	}
	
	public boolean isThereSubmissionAuthor(SubmissionAuthor submissionauthor) throws RepositoryException{
		return submissionAuthors.isThere(submissionauthor);
	}
	//#endif
	
	//AUTHOR
	//#if ${InsertAuthors} == "T"
	public void insertAuthor(Author author) throws AuthorAlreadyInsertedException, RepositoryException{
		authors.insert(author);
	}

	public void removeAuthor(int idAuthor) throws AuthorNotFoundException, RepositoryException, AuthorAlreadyInsertedException{
		authors.remove(idAuthor);  
	}

	public void updateAuthor(Author author) throws AuthorNotFoundException, RepositoryException, AuthorAlreadyInsertedException{
		authors.update(author);
	}

	public List<Author> getAuthors() throws RepositoryException{
		return authors.getAuthors();
	}

	public Author searchAuthor(int idAuthor) throws AuthorNotFoundException, RepositoryException, AuthorAlreadyInsertedException{
		return authors.search(idAuthor);
	} 
	public int getAuthorLastId() throws RepositoryException{
		return authors.getAuthorLastId();
		
	}
	//#endif
}
