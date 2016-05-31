package rise.splcc.facade;

//#if ${Receipt} == "T"
import rise.splcc.business.ReceiptControl;
//#endif
//#if ${Receipt} == "T"
import rise.splcc.data.Receipt;
//#endif
//#if ${Receipt} == "T"
import rise.splcc.exception.ReceiptAlreadyInsertedException;
import rise.splcc.exception.ReceiptNotFoundException;
//#endif
//#if ${Receipt} == "T"
import rise.splcc.repository.ReceiptRepository;
import rise.splcc.repository.ReceiptRepositoryBDR;
//#endif

public class RiSEventFacade {


	//#if ${Receipt} == "T"
	private ReceiptControl receipts;
	//#endif
	
	public void init(){
		
		original();

		//#if ${Receipt} == "T"
		ReceiptRepository receiptRepository = ReceiptRepositoryBDR.getInstance();
		//#endif
		//#if ${Receipt} == "T"
		receipts = new ReceiptControl(receiptRepository);
		//#endif
	}
	
	//RECEIPT
	//#if ${Receipt} == "T"
	public void insertReceipt(Receipt receipt) throws ReceiptAlreadyInsertedException, RepositoryException{
		this.receipts.insert(receipt);
	}
	
	public List<Receipt> getReceipts() throws RepositoryException{
		return receipts.getReceipts();
	}
	
	public int getReceiptLastId() throws RepositoryException{
		return receipts.getReceiptLastId();
	}
	
	public void removeReceipt(int idReceipt) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		receipts.remove(idReceipt);  
	}
	
	public void updateReceipt(Receipt review) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		receipts.update(review);
	}
	
	public Receipt searchReceipt(int idReceipt) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		return receipts.search(idReceipt);
	}
	//#endif
}
