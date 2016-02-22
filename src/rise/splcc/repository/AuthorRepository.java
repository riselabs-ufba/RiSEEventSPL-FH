//#if ${InsertAuthors} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Author;
import rise.splcc.exception.AuthorNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface AuthorRepository {
	
	public void insert(Author author) throws RepositoryException;

	public void remove(int idAuthor) throws AuthorNotFoundException, RepositoryException;

	public Author search(int idAuthor) throws AuthorNotFoundException, RepositoryException;
	
	public List<Author> getAuthors() throws RepositoryException;

	public void update(Author author) throws AuthorNotFoundException, RepositoryException;

	public boolean isThere(int idAuthor) throws RepositoryException;
	
	public int getAuthorLastId() throws RepositoryException;


}
//#endif