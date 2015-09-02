package rise.splcc.facade;

//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.business.CheckingCopyControl;
//#endif

//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.data.CheckingCopy;
//#endif

//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.exception.CheckingCopyAlreadyInsertedException;
import rise.splcc.exception.CheckingCopyNotFoundException;
//#endif

//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.repository.CheckingCopyRepository;
import rise.splcc.repository.CheckingCopyRepositoryBDR;
//#endif



public class RiSEventFacade {

	//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
	private CheckingCopyControl checkingCopys;
	//#endif
	
	public void init(){
		original();
		
		//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		CheckingCopyRepository checkingCopyRepository = CheckingCopyRepositoryBDR.getInstance();
		//#endif
		
		//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		checkingCopys = new CheckingCopyControl(checkingCopyRepository);
		//#endif
		
	}

	//CHECKINGCOPY
	//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
	public void insertCheckingCopy(CheckingCopy checkingCopy) throws RepositoryException, CheckingCopyAlreadyInsertedException{
		this.checkingCopys.insert(checkingCopy);
	}
	
	public List<CheckingCopy> getCheckingCopys() throws RepositoryException{
		return checkingCopys.getCheckingCopys();
	}
	
	public int getCheckingCopyLastId() throws RepositoryException{
		return checkingCopys.getCheckingCopyLastId();
	}
	
	public void removeCheckingCopy(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		checkingCopys.remove(idCheckingCopy);  
	}
	
	public void updateCheckingCopy(CheckingCopy checkingCopy) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		checkingCopys.update(checkingCopy);
	}
	
	public CheckingCopy searchCheckingCopy(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		return checkingCopys.search(idCheckingCopy);
	}
	//#endif
}
