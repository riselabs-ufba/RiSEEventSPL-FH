package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Registration;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class RegistrationRepositoryBDR implements RegistrationRepository {
	
	private static RegistrationRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public RegistrationRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static RegistrationRepositoryBDR getInstance(){
		if(instance == null){
			instance = new RegistrationRepositoryBDR();
		}
		return instance;
	}

	@Override
	public void insert(Registration registration) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO registration (idUser,idEvent, totalValue) "
					+ "Values('"+registration.getIdUser() +"', '"
								+registration.getIdEvent() +"', '"
					            +registration.getTotalValue()+"')");
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
	public void remove(int idRegistration) throws RegistrationNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM registration WHERE idRegistration = '"+ idRegistration+"'");
            if (i == 0) {
            	throw new RegistrationNotFoundException(idRegistration);
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
	public List<Registration> getRegistrations() throws RepositoryException {
		Registration registration = null;
		ArrayList<Registration> list = new ArrayList<Registration>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from registration");
            while (resultset.next()) {
                registration = new Registration();
                registration.setIdRegistration(resultset.getInt("idRegistration"));
                registration.setIdUser(resultset.getInt("idUser"));
                registration.setIdEvent(resultset.getInt("idEvent"));
                registration.setTotalValue(resultset.getFloat("totalValue"));
            
				list.add(registration);
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
	public Registration search(int idRegistration) throws RegistrationNotFoundException,
			RepositoryException {
		Registration registration = null;
		registration = new Registration();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from registration WHERE idRegistration =" + idRegistration +"'");
            if (resultset.next()) {   
                 registration.setIdRegistration(resultset.getInt("idRegistration"));
                 registration.setIdUser(resultset.getInt("idUser"));
                 registration.setIdEvent(resultset.getInt("idEvent"));
                 registration.setTotalValue(resultset.getFloat("totalValue"));
            	
            } else {
            	throw new RegistrationNotFoundException(idRegistration);
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
		return registration;
	}

	@Override
	public void update(Registration registration) throws RegistrationNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE registration SET idUser = '"+ registration.getIdUser() +
    	    									"', idEvent = '" + registration.getIdEvent()+
    	    										 "', totalValue = '" + registration.getTotalValue()+
    	    		                                 "' WHERE idRegistration = '"+ registration.getIdRegistration()+"'");

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
	public boolean isThere(int idRegistration) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM registration WHERE idRegistration = '" + idRegistration + "'");
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
	
	public void addValue(float value, int idRegistration) throws RepositoryException{
		Registration registration = new Registration();
		float totalValue = 0;
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
    	    ResultSet resultset = statement.executeQuery("Select * from registration WHERE idRegistration =" + idRegistration);
    	    if (resultset.next()) {   
                registration.setIdRegistration(resultset.getInt("idRegistration"));
                registration.setIdUser(resultset.getInt("idUser"));
                registration.setIdEvent(resultset.getInt("idEvent"));
                registration.setTotalValue(resultset.getFloat("totalValue"));
    	    }
    	    totalValue = registration.getTotalValue();
    	    registration.setTotalValue(totalValue + value);
    	    statement.executeUpdate("UPDATE registration SET idUser = '"+ registration.getIdUser() +
    	    									"', idEvent = '" + registration.getIdEvent()+
    	    										 "', totalValue = '" + registration.getTotalValue()+
    	    		                                 "' WHERE idRegistration = '"+ registration.getIdRegistration()+"'");

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
	
	public void removeValue(float value, int idRegistration) throws RepositoryException{
		Registration registration = new Registration();
		float totalValue = 0;
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
    	    ResultSet resultset = statement.executeQuery("Select * from registration WHERE idRegistration =" + idRegistration);
    	    if (resultset.next()) {   
                registration.setIdRegistration(resultset.getInt("idRegistration"));
                registration.setIdUser(resultset.getInt("idUser"));
                registration.setIdEvent(resultset.getInt("idEvent"));
                registration.setTotalValue(resultset.getFloat("totalValue"));
    	    }
    	    totalValue = registration.getTotalValue();
    	    registration.setTotalValue(totalValue - value);
    	    statement.executeUpdate("UPDATE registration SET idUser = '"+ registration.getIdUser() +
    	    									"', idEvent = '" + registration.getIdEvent()+
    	    										 "', totalValue = '" + registration.getTotalValue()+
    	    		                                 "' WHERE idRegistration = '"+ registration.getIdRegistration()+"'");

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
	public int getRegistrationLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'registration'");
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
	
	public int search(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException{
		int idRegistration = -1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select idRegistration from registration WHERE idUser =" + idUser + "and idEvent=" + idEvent + "'");
            if (resultset.next()) {   
                 idRegistration = resultset.getInt("idRegistration");
            	
            } else {
            	throw new RegistrationNotFoundException(idRegistration);
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
		return idRegistration;
	}

}
