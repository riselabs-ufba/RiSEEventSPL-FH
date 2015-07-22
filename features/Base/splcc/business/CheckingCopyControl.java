//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.business;

import java.io.IOException;
import java.util.List;


import com.lowagie.text.DocumentException;

import rise.splcc.data.CheckingCopy;

import rise.splcc.exception.CheckingCopyAlreadyInsertedException;
import rise.splcc.exception.CheckingCopyNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.CheckingCopyRepository;



public class CheckingCopyControl {
	
    private CheckingCopyRepository checkingCopys;
	
	public CheckingCopyControl(CheckingCopyRepository repository){
		this.checkingCopys = repository;
	}

	
	public void insert(CheckingCopy checkingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException{
		if (checkingCopy != null) {
			if (!checkingCopys.isThere(checkingCopy.getIdCheckingCopy())) 
				checkingCopys.insert(checkingCopy);
			else
				throw new CheckingCopyAlreadyInsertedException(checkingCopy.getIdCheckingCopy());
		} else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idCheckingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException, CheckingCopyNotFoundException{
		checkingCopys.remove(idCheckingCopy);
	}
	
	public void update(CheckingCopy checkingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException, CheckingCopyNotFoundException{
		checkingCopys.update(checkingCopy);
	}
	
	
	public List<CheckingCopy> getCheckingCopys() throws RepositoryException {
		return checkingCopys.getCheckingCopys();  
	}
	
	public int getCheckingCopyLastId() throws RepositoryException{
		return checkingCopys.getCheckingCopyLastId();
	}

	public CheckingCopy search(int idCheckingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException, CheckingCopyNotFoundException{
		return checkingCopys.search(idCheckingCopy);
	}
	
	//#if ${CheckingCopyAtestado} == "T"
	public void emitirAtestado(String nome, String evento, String periodo, CheckingCopy checkingcopy) throws RepositoryException{
		checkingcopy.emitirAtestado(nome, evento, periodo);
	}
	//#endif
	
	//#if ${CheckingCopyCertificado} == "T"
	public void emitirCertificado(String nome, String evento, String periodo, String atividade, CheckingCopy checkingcopy) throws RepositoryException, DocumentException, IOException{
		checkingcopy.emitirCertificado(nome, evento, periodo, atividade);
	}
	//#endif
}
//#endif