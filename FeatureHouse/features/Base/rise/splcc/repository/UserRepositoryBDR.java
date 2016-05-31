 
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.User;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserNotFoundException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class UserRepositoryBDR implements UserRepository {

	
	
	private static UserRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public UserRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static UserRepositoryBDR getInstance(){
		if(instance == null){
			instance = new UserRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert(User user) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO User Values('"+user.getIdUser()+"','" + user.getPassword()+ "', '"+user.getNameUser()+"', '"+user.getTypeUser()+"', '"+user.getEmail() +"', '"+user.getFiliation()+"')");
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
	public void remove(int idUser) throws UserNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM User WHERE idUser = '"+ idUser+"'");
            if (i == 0) {
            	throw new UserNotFoundException(idUser);
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
	public User search(int idUser) throws UserNotFoundException,
			RepositoryException{
		User user = new User();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from User WHERE idUser =" + idUser);
            if (resultset.next()) {   
            	user.setIdUser(resultset.getInt("idUser"));
            	user.setPassword(resultset.getString("password"));
            	user.setNameUser(resultset.getString("nameUser"));       	
            	user.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	user.setEmail(resultset.getString("email"));
            	user.setFiliation(resultset.getString("filiation"));
            	resultset.close();
            } else {
            	throw new UserNotFoundException(idUser);
            }
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
        return user;
	}
	
	@Override
	public List<User> getUsers() throws RepositoryException {
		User user = null;
		List<User> list = new ArrayList<User>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from User");
            while (resultset.next()) {
                user = new User();
                user.setIdUser(resultset.getInt("idUser"));
            	user.setPassword(resultset.getString("password"));
            	user.setNameUser(resultset.getString("nameUser"));
            	user.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	user.setEmail(resultset.getString("email"));
            	user.setFiliation(resultset.getString("filiation"));
            	
				list.add(user);
				
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
	public void update(User user) throws UserNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
            int i = statement.executeUpdate("UPDATE User SET password = '"+ 
    	                                     user.getPassword() +
    	                                     "', nameUser = '"+ user.getNameUser() +
    	                                     "', typeUser = '"+ user.getTypeUser() +
    	                                     "',email = '"+ user.getEmail() +
    	                                     "', filiation = '" + user.getFiliation() +
    	                                     "' WHERE idUser = '"+ user.getIdUser()+"'");
            if (i == 0) {
            	throw new UserNotFoundException(user.getIdUser());
            }
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
            ResultSet resultset = statement.executeQuery("SELECT * FROM User WHERE idUser = '" + idUser + "'");
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
	
	public int getUserIdByName(String userName) throws RepositoryException{
		int answer = -1;
		try{
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("Select idUser from User where nameUser = '" + userName + "'");
			resultset.first();
			answer = resultset.getInt("idUser");
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
