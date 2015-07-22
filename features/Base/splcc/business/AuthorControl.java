//#if ${InsertAuthors} == "T"
package rise.splcc.business;

import java.util.List;

import rise.splcc.data.Author;
import rise.splcc.exception.AuthorAlreadyInsertedException;
import rise.splcc.exception.AuthorNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.AuthorRepository;

public class AuthorControl {
	 private AuthorRepository authors;
		
		public AuthorControl(AuthorRepository repository){
			this.authors = repository;
		}
		
		public void insert(Author author) throws AuthorAlreadyInsertedException, RepositoryException{
			if (author != null) {
	            if (!authors.isThere(author.getIdAuthor())) {
	                authors.insert(author);
	            } else {
	                throw new AuthorAlreadyInsertedException(author.getIdAuthor());
	            }
	        } else {
	            throw new IllegalArgumentException();
	        }
		}

		public void remove(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authors.remove(idAuthor);
		}
		
		public void update(Author author) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authors.update(author);
		}
		
		public Author search(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			return authors.search(idAuthor);
		}

		public boolean isThere(int idAuthor) throws RepositoryException {
			return authors.isThere(idAuthor);
		}

		public List<Author> getAuthors() throws RepositoryException {
			return authors.getAuthors();  
		}
		public int getAuthorLastId() throws RepositoryException{
			return authors.getAuthorLastId();
		}
	}
//#endif