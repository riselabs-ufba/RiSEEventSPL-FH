//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.CheckingCopy;
import rise.splcc.data.CheckingCopy.TypeCheckingCopy;
import rise.splcc.exception.CheckingCopyNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class CheckingCopyRepositoryBDR implements CheckingCopyRepository {
	
	private static CheckingCopyRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public CheckingCopyRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static CheckingCopyRepositoryBDR getInstance(){
		if(instance == null){
			instance = new CheckingCopyRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert(CheckingCopy checkingCopy) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO CheckingCopy (idRegistration, idUser, dateOfIssue, checkingCopytype) Values('"+checkingCopy.getIdRegistration()+"', '"+checkingCopy.getIdUser()+"', '"+checkingCopy.getDateOfIssue()+"', '"+checkingCopy.getCheckingCopyType()+"')");
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
	public List<CheckingCopy> getCheckingCopys() throws RepositoryException {
		CheckingCopy checkingCopy = null;
		ArrayList<CheckingCopy> list = new ArrayList<CheckingCopy>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from checkingCopy");
            while (resultset.next()) {
                checkingCopy = new CheckingCopy();
                checkingCopy.setIdCheckingCopy(resultset.getInt("idCheckingCopy"));
                checkingCopy.setIdRegistration(resultset.getInt("idRegistration"));
                checkingCopy.setIdUser(resultset.getInt("idUser"));
                checkingCopy.setDateOfIssue(resultset.getString("dateOfIssue"));
                checkingCopy.setCheckingCopyType(TypeCheckingCopy.valueOf(resultset.getString("checkingCopytype")));
            
				list.add(checkingCopy);
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
	public boolean isThere(int idCheckingCopy) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM CheckingCopy WHERE idCheckingCopy = '" + idCheckingCopy + "'");
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
	public int getCheckingCopyLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'CheckingCopy'");
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
	public void remove(int idCheckingCopy) throws CheckingCopyNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM CheckingCopy WHERE idCheckingCopy = '"+ idCheckingCopy+"'");
            if (i == 0) {
            	throw new CheckingCopyNotFoundException(idCheckingCopy);
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
	public void update(CheckingCopy checkingCopy) throws CheckingCopyNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

            		statement.executeUpdate("UPDATE CheckingCopy SET idRegistration = '"+checkingCopy.getIdRegistration()+"', idUser = '"+checkingCopy.getIdUser()+"', dateOfIssue = '"+ checkingCopy.getDateOfIssue() +"', checkingCopyType = '" + checkingCopy.getCheckingCopyType()+ "' WHERE idCheckingCopy = '"+ checkingCopy.getIdCheckingCopy()+"'");
            	
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
	public CheckingCopy search(int idCheckingCopy) throws CheckingCopyNotFoundException,
			RepositoryException {
		CheckingCopy checkingCopy = null;
		checkingCopy = new CheckingCopy();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from checkingCopy WHERE idCheckingCopy =" + idCheckingCopy);
            if (resultset.next()) {   
            	checkingCopy.setIdCheckingCopy(resultset.getInt("idCheckingCopy"));
            	checkingCopy.setIdRegistration(resultset.getInt("idRegistration"));
            	checkingCopy.setIdUser(resultset.getInt("idUser"));
            	checkingCopy.setDateOfIssue(resultset.getString("dateOfIssue"));
            	checkingCopy.setCheckingCopyType(TypeCheckingCopy.valueOf(resultset.getString("checkingCopyType")));
            	
            } else {
            	throw new CheckingCopyNotFoundException(idCheckingCopy);
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
		return checkingCopy;
	}
}
//#endif