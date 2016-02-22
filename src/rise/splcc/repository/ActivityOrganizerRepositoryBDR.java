//#if ${RegistrationOrganizerActivity} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.ActivityOrganizer;
import rise.splcc.exception.ActivityOrganizerNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class ActivityOrganizerRepositoryBDR implements ActivityOrganizerRepository{

	
	private static ActivityOrganizerRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ActivityOrganizerRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ActivityOrganizerRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ActivityOrganizerRepositoryBDR();
		}
		return instance;
	}
	
	@Override
	public void insert(ActivityOrganizer activityOrganizer) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO activityOrganizer (idActivity,IdUser) "
					+ "Values('"+activityOrganizer.getIdActivity() +"', '"
					            +activityOrganizer.getIdOrganizer()+"')");
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
	public void remove(ActivityOrganizer activityorganizer) throws ActivityOrganizerNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM activityorganizer WHERE idActivity = '"+ activityorganizer.getIdActivity() +"'AND idUser ='"+activityorganizer.getIdOrganizer()+"'");
            if (i == 0) {
            	throw new ActivityOrganizerNotFoundException(activityorganizer.getIdActivity());
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
	public ActivityOrganizer search(int idActivity) throws ActivityOrganizerNotFoundException,
			RepositoryException {
		ActivityOrganizer activityOrganizer = null;
		activityOrganizer = new ActivityOrganizer();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from activityorganizer WHERE idActivity =" + idActivity);
            if (resultset.next()) { 
            	
            	activityOrganizer.setIdActivity(resultset.getInt("idActivity"));
            	activityOrganizer.setIdOrganizer(resultset.getInt("idUser"));
            	
            } else {
            	throw new ActivityOrganizerNotFoundException(idActivity);
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
		return activityOrganizer;
	}


	@Override
	public List<ActivityOrganizer> getActivitiesOrganizers() throws RepositoryException {
		ActivityOrganizer activityOrganizer=null;
		ArrayList<ActivityOrganizer> list = new ArrayList<ActivityOrganizer>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from activityorganizer");
            while (resultset.next()) {
            	activityOrganizer = new ActivityOrganizer();
            	activityOrganizer.setIdActivity(resultset.getInt("idActivity"));
            	activityOrganizer.setIdOrganizer(resultset.getInt("idUser"));
				list.add(activityOrganizer);
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
	public void update(ActivityOrganizer activityOrganizer) throws ActivityOrganizerNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE activityorganizer SET idActivity = '"+ activityOrganizer.getIdActivity() +
    	    		                                 "', idUser = '" + activityOrganizer.getIdOrganizer() +"'");

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
	public boolean isThere(ActivityOrganizer activityOrganizer) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM activityorganizer WHERE idActivity = '" + activityOrganizer.getIdActivity() + "'AND idUser='" + activityOrganizer.getIdOrganizer() +"'");
            //ResultSet resultset = statement.executeQuery("select * from activityorganizer where idActivity = 1 AND idUser = 2;");
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
	public int getActivityOrganizerLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'activityorganizer'");
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
	public List<ActivityOrganizer> getActivitiesById(int idActivity) throws RepositoryException {
		ActivityOrganizer activityOrganizer=null;
		ArrayList<ActivityOrganizer> list = new ArrayList<ActivityOrganizer>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from activityorganizer where idActivity ='"+ idActivity + "'");
            while (resultset.next()) {
            	activityOrganizer = new ActivityOrganizer();
            	activityOrganizer.setIdActivity(resultset.getInt("idActivity"));
            	activityOrganizer.setIdOrganizer(resultset.getInt("idUser"));
				list.add(activityOrganizer);
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
}
//#endif