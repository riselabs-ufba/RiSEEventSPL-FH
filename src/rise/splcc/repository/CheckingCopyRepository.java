//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.CheckingCopy;
import rise.splcc.data.CheckingCopy;
import rise.splcc.exception.CheckingCopyNotFoundException;
import rise.splcc.exception.CheckingCopyNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface CheckingCopyRepository {

	public void insert(CheckingCopy checkingCopy) throws RepositoryException;
	
	public List<CheckingCopy> getCheckingCopys() throws RepositoryException;
	
	public boolean isThere(int idCheckingCopy) throws RepositoryException;
	
	public int getCheckingCopyLastId() throws RepositoryException;
	
	public void remove(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException;
	
	public void update(CheckingCopy checkingCopy) throws CheckingCopyNotFoundException, RepositoryException;

	public CheckingCopy search(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException;
	
}
//#endif