//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Payment;
import rise.splcc.exception.PaymentNotFoundException;
import rise.splcc.exception.RepositoryException;


public interface PaymentRepository {

	public void insert(Payment payment) throws RepositoryException;
	
	public List<Payment> getPayments() throws RepositoryException;
	
	public boolean isThere(int idPayment) throws RepositoryException;

	public int getPaymentLastId() throws RepositoryException;

	public void update(Payment payment) throws PaymentNotFoundException, RepositoryException;

	public void remove(int idPayment) throws PaymentNotFoundException, RepositoryException;

	public Payment search(int idPayment) throws PaymentNotFoundException, RepositoryException;
	
}
//#endif