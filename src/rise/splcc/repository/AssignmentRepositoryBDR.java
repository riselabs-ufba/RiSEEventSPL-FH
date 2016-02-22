//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Assignment;
import rise.splcc.exception.AssignmentNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class AssignmentRepositoryBDR implements AssignmentRepository {
	
	private static AssignmentRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public AssignmentRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static AssignmentRepositoryBDR getInstance(){
		if(instance == null){
			instance = new AssignmentRepositoryBDR();
		}
		return instance;
	}

	
	@Override
	public void insert(Assignment assignment) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO assignement (idUser, idReview, date, idSubmission) "
					+ "Values('"+assignment.getIdReviwerUser() +"', '"
					            +assignment.getIdReview()+"', '"
					            +assignment.getDate()+"', '"
					            +assignment.getIdReviewSubmission()+"')");
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
	public void remove(Assignment assignment) throws AssignmentNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM assignement WHERE idUser = '"+ assignment.getIdReviwerUser() +"' AND idReview = '"+ assignment.getIdReview() +"' AND idSubmission = '"+ assignment.getIdReviewSubmission() +"'  ");
            if (i == 0) {
            	throw new AssignmentNotFoundException(assignment.getIdReview());
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
	public Assignment search(Assignment assignment1) throws AssignmentNotFoundException,
			RepositoryException {
		Assignment assignment = null;
		assignment = new Assignment();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from assignement WHERE idUser = '"+ assignment1.getIdReviwerUser() +"' AND idReview = '"+ assignment1.getIdReview() +"' AND idSubmission = '"+ assignment1.getIdReviewSubmission() +"' " );
            if (resultset.next()) {   
            	assignment.setIdReview(resultset.getInt("idReview"));
            	assignment.setIdReviewSubmission(resultset.getInt("idSubmission"));
            	assignment.setIdReviwerUser(resultset.getInt("idUser"));
            	assignment.setDate(resultset.getString("date"));
            	
            } else {
            	throw new AssignmentNotFoundException(assignment.getIdReview());
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
		return assignment;
	}

	@Override
	public List<Assignment> getAssignments() throws RepositoryException {
		Assignment assignment=null;
		ArrayList<Assignment> list = new ArrayList<Assignment>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from assignement");
            while (resultset.next()) {
            	assignment = new Assignment();
            	assignment.setIdReview(resultset.getInt("idReview"));
            	assignment.setIdReviewSubmission(resultset.getInt("idSubmission"));
            	assignment.setIdReviwerUser(resultset.getInt("idUser"));
            	assignment.setDate(resultset.getString("date"));
				list.add(assignment);
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
	public void update(Assignment assignment) throws AssignmentNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE assignement SET idReview = '"+ assignment.getIdReview() +
    	    										 "', idSubmission = '" + assignment.getIdReviewSubmission()+
    	    		                                 "', idUser = '" + assignment.getIdReviwerUser() +
    	    		                                 "', date = '"+ assignment.getDate() + 
    	    		                                 "' WHERE idUser = '"+ assignment.getIdReviwerUser() +"' AND idReview = '"+ assignment.getIdReview() +"' AND idSubmission = '"+ assignment.getIdReviewSubmission() +"' ");

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
	public boolean isThere(Assignment assignment) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM assignement WHERE idUser = '"+ assignment.getIdReviwerUser() +"' AND idReview = '"+ assignment.getIdReview() +"' AND idSubmission = '"+ assignment.getIdReviewSubmission() +"' ");
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