//#if ${InsertAuthors} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.SubmissionAuthor;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SubmissionAuthorNotFoundException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class SubmissionAuthorRepositoryBDR implements SubmissionAuthorRepository {

	
	private static SubmissionAuthorRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public SubmissionAuthorRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static SubmissionAuthorRepositoryBDR getInstance(){
		if(instance == null){
			instance = new SubmissionAuthorRepositoryBDR();
		}
		return instance;
	}
	
	@Override
	public void insert(SubmissionAuthor submissionAuthor)
			throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO SubmissionAuthor (idAuthor,idSubmission,idActivity) "
					+ "Values('"+submissionAuthor.getIdAuthor() 
					+"', '" + submissionAuthor.getIdSubmission() 
					+"', '"  +submissionAuthor.getIdActivity()+"')");
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
	public void remove(SubmissionAuthor submissionAuthor)
			throws SubmissionAuthorNotFoundException, RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM SubmissionAuthor WHERE idActivity = '"+ submissionAuthor.getIdActivity() +"'AND idAuthor ='"+submissionAuthor.getIdAuthor()+"' AND idSubmission = '"+submissionAuthor.getIdSubmission()+"'");
            if (i == 0) {
            	throw new SubmissionAuthorNotFoundException(submissionAuthor.getIdActivity());
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
	public SubmissionAuthor search(SubmissionAuthor submissionAuthor)
			throws SubmissionAuthorNotFoundException, RepositoryException {
		try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from SubmissionAuthor WHERE idAuthor =" + submissionAuthor.getIdAuthor()+ "'AND idSubmission ='"+submissionAuthor.getIdSubmission()+"' AND idActivity = '"+submissionAuthor.getIdActivity()+"'");
            if (resultset.next()) { 
            	
            	submissionAuthor.setIdActivity(resultset.getInt("SubmissionidActivity"));
            	submissionAuthor.setIdAuthor(resultset.getInt("UseridUser"));
            	submissionAuthor.setIdSubmission(resultset.getInt("SubmissionidSubmission"));
            	
            } else {
            	throw new SubmissionAuthorNotFoundException(submissionAuthor.getIdSubmission());
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
		return submissionAuthor;
	}

	@Override
	public List<SubmissionAuthor> getSubmissionAuthors() throws RepositoryException {
		SubmissionAuthor submissionAuthor=null;
		ArrayList<SubmissionAuthor> list = new ArrayList<SubmissionAuthor>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from SubmissionAuthor");
            while (resultset.next()) {
            	submissionAuthor = new SubmissionAuthor();  	
            	submissionAuthor.setIdAuthor(resultset.getInt("idAuthor"));
            	submissionAuthor.setIdSubmission(resultset.getInt("idSubmission"));
            	submissionAuthor.setIdActivity(resultset.getInt("idActivity"));
				list.add(submissionAuthor);
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
	public void update(SubmissionAuthor submissionAuthor)
			throws SubmissionAuthorNotFoundException, RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE SubmissionAuthor SET idActivity = '"+ submissionAuthor.getIdActivity() +
    	    		                                 "', idUser = '" + submissionAuthor.getIdAuthor() +
    	    		                                 "', idSubmission = '" + submissionAuthor.getIdSubmission()
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
	public boolean isThere(SubmissionAuthor submissionAuthor)
			throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM SubmissionAuthor WHERE idActivity = '"+ submissionAuthor.getIdActivity() +"'AND idAuthor ='"+submissionAuthor.getIdAuthor()+"' AND idSubmission = '"+submissionAuthor.getIdSubmission()+"'");
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