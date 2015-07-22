//#if ${Speaker} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Speaker;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SpeakerNotFoundException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class SpeakerRepositoryBDR implements SpeakerRepository{
	
	private static SpeakerRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	
	public SpeakerRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static SpeakerRepositoryBDR getInstance(){
		if(instance == null){
			instance = new SpeakerRepositoryBDR();
		}
		return instance;
	}

	@Override
	public void insert(Speaker speaker) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO User Values('"+speaker.getIdUser()+"','" + speaker.getPassword()+ "', '"+speaker.getNameUser()+"', '"+speaker.getTypeUser()+"', '"+speaker.getEmail() +"', '"+speaker.getFiliation()+"')");
			statement.executeUpdate("INSERT INTO speaker Values('"+speaker.getIdUser()+"','" + speaker.getBiography() +"')");
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
	public void remove(int idUser) throws SpeakerNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM speaker WHERE idUser = '"+ idUser+"'");
            if (i == 0) {
            	throw new SpeakerNotFoundException(idUser);
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
	public Speaker search(int idUser) throws SpeakerNotFoundException,
			RepositoryException {
		Speaker speaker = null;
		speaker = new Speaker();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from speaker as s inner join user on user.idUser = s.idUser and s.idUser = " + idUser);
            if (resultset.next()) {   
            	speaker.setIdUser(resultset.getInt("idUser"));
            	speaker.setBiography(resultset.getString("biography"));
            	speaker.setPassword(resultset.getString("password"));
            	speaker.setNameUser(resultset.getString("nameUser"));       	
            	speaker.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	speaker.setEmail(resultset.getString("email"));
            	speaker.setFiliation(resultset.getString("filiation"));
            } else {
            	throw new SpeakerNotFoundException(idUser);
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
		return speaker;
	}

	@Override
	public List<Speaker> getSpeakers() throws RepositoryException {
		Speaker speaker = null;
		ArrayList<Speaker> list = new ArrayList<Speaker>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select speaker.idUser, password, nameUser, typeUser, email, filiation, biography from speaker inner join User on speaker.idUser = user.idUser;");
          
            while (resultset.next()) {
            	speaker = new Speaker();
            	speaker.setIdUser(resultset.getInt("idUser"));
            	speaker.setPassword(resultset.getString("password"));
            	speaker.setNameUser(resultset.getString("nameUser"));
            	speaker.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	speaker.setEmail(resultset.getString("email"));
            	speaker.setFiliation(resultset.getString("filiation"));
            	speaker.setBiography(resultset.getString("biography"));
				list.add(speaker);
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
	public void update(Speaker speaker) throws SpeakerNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
    	    statement.executeUpdate("UPDATE User SET password = '"+ 
    	    		speaker.getPassword() +
                    "', nameUser = '"+ speaker.getNameUser() +
                    "', typeUser = '"+ speaker.getTypeUser() +
                    "',email = '"+ speaker.getEmail() +
                    "', filiation = '" + speaker.getFiliation() +
                    "' WHERE idUser = '"+ speaker.getIdUser()+"'");
            statement.executeUpdate("UPDATE speaker SET biography = '"+ speaker.getBiography() +"' WHERE idUser = '"+ speaker.getIdUser()+"'");   
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
	public boolean isThere(int idUser) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM speaker WHERE idUser = '" + idUser + "'");
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