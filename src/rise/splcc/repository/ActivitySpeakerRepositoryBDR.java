//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.ActivitySpeaker;
import rise.splcc.exception.ActivitySpeakerNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class ActivitySpeakerRepositoryBDR implements ActivitySpeakerRepository {
	
	private static ActivitySpeakerRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ActivitySpeakerRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ActivitySpeakerRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ActivitySpeakerRepositoryBDR();
		}
		return instance;
	}

	
	@Override
	public void insert(ActivitySpeaker activitySpeaker) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO activitySpeaker (idActivity,IdUser) "
					+ "Values('"+activitySpeaker.getIdActivity() +"', '"
					            +activitySpeaker.getIdSpeaker()+"')");
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
	public void remove(ActivitySpeaker activityspeaker) throws ActivitySpeakerNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM activityspeaker WHERE idActivity = '"+ activityspeaker.getIdActivity() +"'AND idUser ='"+activityspeaker.getIdSpeaker()+"'");
            if (i == 0) {
            	throw new ActivitySpeakerNotFoundException(activityspeaker.getIdActivity());
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
	public ActivitySpeaker search(int idActivity) throws ActivitySpeakerNotFoundException,
			RepositoryException {
		ActivitySpeaker activitySpeaker = null;
		activitySpeaker = new ActivitySpeaker();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from activityspeaker WHERE idActivity =" + idActivity);
            if (resultset.next()) { 
            	
            	activitySpeaker.setIdActivity(resultset.getInt("idActivity"));
            	activitySpeaker.setIdSpeaker(resultset.getInt("idUser"));
            	
            } else {
            	throw new ActivitySpeakerNotFoundException(idActivity);
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
		return activitySpeaker;
	}


	@Override
	public List<ActivitySpeaker> getActivitiesSpeakers() throws RepositoryException {
		ActivitySpeaker activitySpeaker=null;
		ArrayList<ActivitySpeaker> list = new ArrayList<ActivitySpeaker>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from activityspeaker");
            while (resultset.next()) {
            	activitySpeaker = new ActivitySpeaker();
            	activitySpeaker.setIdActivity(resultset.getInt("idActivity"));
            	activitySpeaker.setIdSpeaker(resultset.getInt("idUser"));
				list.add(activitySpeaker);
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
	public void update(ActivitySpeaker activitySpeaker) throws ActivitySpeakerNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE activityspeaker SET idActivity = '"+ activitySpeaker.getIdActivity() +
    	    		                                 "', idUser = '" + activitySpeaker.getIdSpeaker() +"'");

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
	public boolean isThere(ActivitySpeaker activitySpeaker) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM activityspeaker WHERE idActivity = '" + activitySpeaker.getIdActivity() + "'AND idUser='" + activitySpeaker.getIdSpeaker() +"'");
            //ResultSet resultset = statement.executeQuery("select * from activityspeaker where idActivity = 1 AND idUser = 2;");
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
	public int getActivitySpeakerLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'activityspeaker'");
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
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException {
		ActivitySpeaker activitySpeaker=null;
		ArrayList<ActivitySpeaker> list = new ArrayList<ActivitySpeaker>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from activityspeaker where idActivity ='"+ idActivity + "'");
            while (resultset.next()) {
            	activitySpeaker = new ActivitySpeaker();
            	activitySpeaker.setIdActivity(resultset.getInt("idActivity"));
            	activitySpeaker.setIdSpeaker(resultset.getInt("idUser"));
				list.add(activitySpeaker);
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