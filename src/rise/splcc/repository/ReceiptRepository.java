//#if ${Receipt} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Receipt;
import rise.splcc.exception.ReceiptNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface ReceiptRepository {

	public void insert(Receipt receipt) throws RepositoryException;
	
	public List<Receipt> getReceipts() throws RepositoryException;
	
	public boolean isThere(int idReceipt) throws RepositoryException;
	
	public int getReceiptLastId() throws RepositoryException;
	
	public void remove(int idReceipt) throws ReceiptNotFoundException, RepositoryException;
	
	public void update(Receipt receipt) throws ReceiptNotFoundException, RepositoryException;
	
	public Receipt search(int idReceipt) throws ReceiptNotFoundException, RepositoryException;
}
//#endif