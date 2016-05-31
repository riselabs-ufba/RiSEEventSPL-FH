package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class EventRepositoryBDR implements EventRepository {
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException{
		ArrayList<String> list = new ArrayList<String>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select nameUser from User inner join activityuser on user.idUser = activityuser.idUser inner join activity on activity.idActivity = activityuser.idActivity where activity.idEvent ='"+ idEvent + "'");
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
