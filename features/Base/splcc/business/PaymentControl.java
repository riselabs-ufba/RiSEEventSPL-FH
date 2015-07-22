//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.business;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import rise.splcc.data.Payment;
import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.PaymentRepository;


public class PaymentControl {
	
    private PaymentRepository payments;
	
	public PaymentControl(PaymentRepository repository){
		this.payments = repository;
	}

	
	public void insert(Payment payment) throws PaymentAlreadyInsertedException, RepositoryException{
		if (payment != null) {
			if (!payments.isThere(payment.getIdPayment())) 
				payments.insert(payment);
			else
				throw new PaymentAlreadyInsertedException(payment.getIdPayment());
		} else {
            throw new IllegalArgumentException();
        }
	}
	
	
	
	public List<Payment> getPayments() throws RepositoryException {
		return payments.getPayments();  
	}


	public int getPaymentLastId() throws RepositoryException{
		return payments.getPaymentLastId();
	}


	public void update(Payment payment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		payments.update(payment);
	}


	public void remove(int idPayment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		payments.remove(idPayment);
	}


	public Payment search(int idPayment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		return payments.search(idPayment);
	}

	public void type(Payment payment, Payment out) throws DocumentException, IOException {
		payment.startarAcaoTypePayment(out);
	}
}
//#endif