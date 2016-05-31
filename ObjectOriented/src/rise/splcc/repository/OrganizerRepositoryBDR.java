//#if ${Organizer} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Organizer;
import rise.splcc.data.Organizer.TypeOrganizer;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.OrganizerNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class OrganizerRepositoryBDR implements OrganizerRepository{

	private static OrganizerRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public OrganizerRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static OrganizerRepositoryBDR getInstance(){
		if(instance == null){
			instance = new OrganizerRepositoryBDR();
		}
		return instance;
	}

	@Override
	public void insert(Organizer organizer) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO User Values('"+organizer.getIdUser()+"','" + organizer.getPassword()+ "', '"+organizer.getNameUser()+"', '"+organizer.getTypeUser()+"', '"+organizer.getEmail() +"', '"+organizer.getFiliation()+"')");
			statement.executeUpdate("INSERT INTO Organizer Values('"+organizer.getIdUser()+
					                "','" + organizer.getTypeOrganizer()+"')");
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
	public void remove(int idUser) throws OrganizerNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM Organizer WHERE idUser = '"+ idUser+"'");
            if (i == 0) {
            	throw new OrganizerNotFoundException(idUser);
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
	public Organizer search(int idUser) throws OrganizerNotFoundException,
			RepositoryException {
		Organizer organizer = null;
		organizer = new Organizer();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from organizer as o inner join user on user.idUser = o.idUser and o.idUser =" + idUser);
            if (resultset.next()) {   
            	organizer.setIdUser(resultset.getInt("idUser"));
            	organizer.setTypeOrganizer(TypeOrganizer.valueOf(resultset.getString("typeOrganizer")));
            	organizer.setPassword(resultset.getString("password"));
            	organizer.setNameUser(resultset.getString("nameUser"));       	
            	organizer.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	organizer.setEmail(resultset.getString("email"));
            	organizer.setFiliation(resultset.getString("filiation"));
            } else {
            	throw new OrganizerNotFoundException(idUser);
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
		return organizer;
	}

	@Override
	public List<Organizer> getOrganizers() throws RepositoryException {
		Organizer organizer = null;
		ArrayList<Organizer> list = new ArrayList<Organizer>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select Organizer.idUser, password, nameUser, typeUser, email, filiation, typeOrganizer from Organizer inner join User on Organizer.idUser = User.idUser;");
            while (resultset.next()) {
            	organizer = new Organizer();
            	organizer.setIdUser(resultset.getInt("idUser"));
            	organizer.setPassword(resultset.getString("password"));
            	organizer.setNameUser(resultset.getString("nameUser"));
            	organizer.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	organizer.setEmail(resultset.getString("email"));
            	organizer.setFiliation(resultset.getString("filiation"));
            	organizer.setTypeOrganizer(TypeOrganizer.valueOf(resultset.getString("typeOrganizer")));
				list.add(organizer);
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
	public void update(Organizer organizer) throws OrganizerNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
            statement.executeUpdate("UPDATE Organizer SET typeOrganizer = '"+ organizer.getTypeOrganizer() +"' WHERE idUser = '"+ organizer.getIdUser()+"'");
            statement.executeUpdate("UPDATE User SET password = '"+ 
            		organizer.getPassword() +
                    "', nameUser = '"+ organizer.getNameUser() +
                    "', typeUser = '"+ organizer.getTypeUser() +
                    "',email = '"+ organizer.getEmail() +
                    "', filiation = '" + organizer.getFiliation() +
                    "' WHERE idUser = '"+ organizer.getIdUser()+"'");
            
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
            ResultSet resultset = statement.executeQuery("SELECT * FROM Organizer WHERE idUser = '" + idUser + "'");
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