package rise.splcc.facade;

//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.business.PaymentControl;
//#endif
//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.data.Payment;
//#endif
//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
//#endif
//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.repository.PaymentRepository;
import rise.splcc.repository.PaymentRepositoryBDR;
//#endif

public class RiSEventFacade {

	//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
	private PaymentControl payments;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		PaymentRepository paymentRepository = PaymentRepositoryBDR.getInstance();
		//#endif
		
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		payments = new PaymentControl(paymentRepository);
		//#endif
	}
	
	//PAYMENT
	//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
	public void insertPayment(Payment payment) throws PaymentAlreadyInsertedException, RepositoryException{
		this.payments.insert(payment);
	}
	
	public List<Payment> getPayments() throws RepositoryException{
		return payments.getPayments();
	}
	
	public int getPaymentLastId() throws RepositoryException{
		return payments.getPaymentLastId();
	}
	
	public void updatePayment(Payment payment) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		payments.update(payment);
	}
	
	public void removePayment(int idPayment) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		payments.remove(idPayment);  
	}
	
	public Payment searchPayment(int idPayment) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		return payments.search(idPayment);
	}
	
	public void typePayment(Payment payment, Payment paymentout) throws DocumentException, IOException {
		this.payments.type(payment, paymentout);
	}
	//#endif
	
}
