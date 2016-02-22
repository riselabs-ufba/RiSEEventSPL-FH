//#if ${Receipt} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Receipt;
import rise.splcc.exception.ReceiptNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class ReceiptRepositoryBDR implements ReceiptRepository {
	
	private static ReceiptRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ReceiptRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ReceiptRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ReceiptRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert(Receipt receipt) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO receipt (idReceipt, idPayment, dateOfIssue, totalValue) Values('"+receipt.getIdReceipt()+"', '"+receipt.getIdPayment()+"', '"+ receipt.getDateOfIssue() +"', '" + receipt.getTotalValue()+ "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
	}
	


	@Override
	public List<Receipt> getReceipts() throws RepositoryException {
		Receipt receipt = null;
		ArrayList<Receipt> list = new ArrayList<Receipt>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from Receipt");
            while (resultset.next()) {
                receipt = new Receipt();
                receipt.setIdReceipt(resultset.getInt("idReceipt"));
                receipt.setIdPayment(resultset.getInt("idPayment"));
                receipt.setDateOfIssue(resultset.getString("dateOfIssue"));
                receipt.setTotalValue(resultset.getFloat("totalValue"));
            
				list.add(receipt);
            } 
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return list;
	}

	@Override
	public boolean isThere(int idReceipt) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM receipt WHERE idReceipt = '" + idReceipt + "'");
            answer = resultset.next();
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}

	@Override
	public int getReceiptLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'receipt'");
            resultset.first();
            answer = resultset.getInt("proximo_valor");
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}
	
	@Override
	public void remove(int idReceipt) throws ReceiptNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM review WHERE idReceipt = '"+ idReceipt+"'");
            if (i == 0) {
            	throw new ReceiptNotFoundException(idReceipt);
            }
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch(SQLException e){
            throw new RepositoryException(e);            
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}
	
	@Override
	public void update(Receipt review) throws ReceiptNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

            		statement.executeUpdate("UPDATE review SET idPayment = '"+ review.getIdPayment() +"', totalValue = '"+ review.getTotalValue() +"', dateOfIssue = '"+ review.getDateOfIssue() +"' WHERE idReceipt = '"+ review.getIdReceipt()+"'");

		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch (SQLException e) {
		    throw new RepositoryException(e);
	    } finally {
	    	try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}
	
	@Override
	public Receipt search(int idReceipt) throws ReceiptNotFoundException,
			RepositoryException {
		Receipt review = null;
		review = new Receipt();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from review WHERE idReceipt =" + idReceipt);
            if (resultset.next()) {   
            	review.setIdReceipt(resultset.getInt("idReceipt"));
            	review.setIdPayment(resultset.getInt("idPayment"));
            	review.setTotalValue(resultset.getFloat("totalValue"));
            	review.setDateOfIssue(resultset.getString("dateOfIssue"));
            	
            } else {
            	throw new ReceiptNotFoundException(idReceipt);
            }
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return review;
	}


}
//#endif