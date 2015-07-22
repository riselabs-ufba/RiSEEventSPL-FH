//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.SubmissionUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SubmissionUserNotFoundException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class SubmissionUserRepositoryBDR implements SubmissionUserRepository{
	
	private static SubmissionUserRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public SubmissionUserRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static SubmissionUserRepositoryBDR getInstance(){
		if(instance == null){
			instance = new SubmissionUserRepositoryBDR();
		}
		return instance;
	}
	
	@Override
	public void insert(SubmissionUser submissionUser) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO SubmissionUser (idUser,idSubmission,idActivity) "
					+ "Values('"+submissionUser.getIdUser() 
					+"', '" + submissionUser.getIdSubmission() 
					+"', '"  +submissionUser.getIdActivity()+"')");
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
	public void remove(SubmissionUser submissionUser) throws SubmissionUserNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM User_Submission WHERE SubmissionidActivity = '"+ submissionUser.getIdActivity() +"'AND UseridUser ='"+submissionUser.getIdUser()+"' AND SubmissionidSubmission = '"+submissionUser.getIdSubmission()+"'");
            if (i == 0) {
            	throw new SubmissionUserNotFoundException(submissionUser.getIdActivity());
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
	public SubmissionUser search(SubmissionUser submissionUser) throws SubmissionUserNotFoundException,
			RepositoryException {
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from SubmissionUser WHERE idUser =" + submissionUser.getIdUser()+ "'AND idSubmission ='"+submissionUser.getIdSubmission()+"' AND idActivity = '"+submissionUser.getIdActivity()+"'");
            if (resultset.next()) { 
            	
            	submissionUser.setIdActivity(resultset.getInt("SubmissionidActivity"));
            	submissionUser.setIdUser(resultset.getInt("UseridUser"));
            	submissionUser.setIdSubmission(resultset.getInt("SubmissionidSubmission"));
            	
            } else {
            	throw new SubmissionUserNotFoundException(submissionUser.getIdSubmission());
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
		return submissionUser;
	}
	
	@Override
	public List<SubmissionUser> getSubmissionUsers() throws RepositoryException {
		SubmissionUser submissionUser=null;
		ArrayList<SubmissionUser> list = new ArrayList<SubmissionUser>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from SubmissionUser");
            while (resultset.next()) {
            	submissionUser = new SubmissionUser();  	
            	submissionUser.setIdUser(resultset.getInt("idUser"));
            	submissionUser.setIdSubmission(resultset.getInt("idSubmission"));
            	submissionUser.setIdActivity(resultset.getInt("idActivity"));
				list.add(submissionUser);
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
	public void update(SubmissionUser submissionUser) throws SubmissionUserNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE SubmissionUser SET idActivity = '"+ submissionUser.getIdActivity() +
    	    		                                 "', idUser = '" + submissionUser.getIdUser() +
    	    		                                 "', idSubmission = '" + submissionUser.getIdSubmission()
    	    		                                 +"'");

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
	public boolean isThere(SubmissionUser submissionUser) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM SubmissionUser WHERE idActivity = '"+ submissionUser.getIdActivity() +"'AND idUser ='"+submissionUser.getIdUser()+"' AND idSubmission = '"+submissionUser.getIdSubmission()+"'");
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
	

	

	
}
//#endif