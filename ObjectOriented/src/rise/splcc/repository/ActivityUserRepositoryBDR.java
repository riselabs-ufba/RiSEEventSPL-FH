//#if ${RegistrationUserActivity} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.ActivityUser;
import rise.splcc.exception.ActivityUserNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class ActivityUserRepositoryBDR implements ActivityUserRepository{
	
	private static ActivityUserRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ActivityUserRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ActivityUserRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ActivityUserRepositoryBDR();
		}
		return instance;
	}
	
	@Override
	public void insert(ActivityUser activityUser) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO activityuser (idActivity,frequency,IdUser) "
					+ "Values('"+activityUser.getIdActivity() 
					+"', '" + activityUser.getFrequency() 
					+"', '"  +activityUser.getIdUser()+"')");
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
	public void remove(ActivityUser activityUser) throws ActivityUserNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM activityuser WHERE idActivity = '"+ activityUser.getIdActivity() +"'AND idUser ='"+activityUser.getIdUser()+"'");
            if (i == 0) {
            	throw new ActivityUserNotFoundException(activityUser.getIdActivity());
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
	public ActivityUser search(int idActivity) throws ActivityUserNotFoundException,
			RepositoryException {
		ActivityUser activityUser = null;
		activityUser = new ActivityUser();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from activityuser WHERE idActivity =" + idActivity);
            if (resultset.next()) { 
            	
            	activityUser.setIdActivity(resultset.getInt("idActivity"));
            	activityUser.setIdUser(resultset.getInt("idUser"));
            	activityUser.setFrequency(resultset.getFloat("frequency"));
            	
            } else {
            	throw new ActivityUserNotFoundException(idActivity);
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
		return activityUser;
	}
	
	@Override
	public List<ActivityUser> getActivitiesUsers() throws RepositoryException {
		ActivityUser activityUser=null;
		ArrayList<ActivityUser> list = new ArrayList<ActivityUser>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from activityuser");
            while (resultset.next()) {
            	activityUser = new ActivityUser();
            	activityUser.setIdActivity(resultset.getInt("idActivity"));
            	activityUser.setIdUser(resultset.getInt("idUser"));
            	activityUser.setFrequency(resultset.getFloat("frequency"));
				list.add(activityUser);
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
	public void update(ActivityUser activityUser) throws ActivityUserNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE activityuser SET idActivity = '"+ activityUser.getIdActivity() +
    	    		                                 "', idUser = '" + activityUser.getIdUser() +
    	    		                                 "', frequency = '" + activityUser.getFrequency()
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
	public boolean isThere(ActivityUser activityUser) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM activityuser WHERE idActivity = '" + activityUser.getIdActivity() + "'AND idUser='" + activityUser.getIdUser() +"'");
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
	public int getActivityUserLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'activityuser'");
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
	public List<ActivityUser> getActivitiesById(int idActivity) throws RepositoryException {
		ActivityUser userActivity=null;
		ArrayList<ActivityUser> list = new ArrayList<ActivityUser>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from activityuser where idActivity ='"+ idActivity + "'");
            while (resultset.next()) {
            	userActivity= new ActivityUser();
            	userActivity.setIdActivity(resultset.getInt("idActivity"));
            	userActivity.setIdUser(resultset.getInt("idUser"));
				list.add(userActivity);
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
	//#if ${ReportsFrequencyperActivity} == "T"
	@Override
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		ArrayList<String> list = new ArrayList<String>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select nameUser from User inner join activityuser on user.idUser = activityuser.idUser and activityuser.idActivity ='"+ idActivity + "'");
            while (resultset.next()) {
            	String nameUser;
            	nameUser = resultset.getString("nameUser");
				list.add(nameUser);
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
	//#endif
}
//#endif