package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Registration;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface RegistrationRepository {

	public List<Registration> getRegistrations() throws RepositoryException;

	public Registration search(int idRegistration) throws RegistrationNotFoundException, RepositoryException;
	
	public void insert(Registration registration) throws RepositoryException;

	public void remove(int idRegistration) throws RegistrationNotFoundException, RepositoryException;
	
	public void update(Registration registration) throws RegistrationNotFoundException, RepositoryException;

	public boolean isThere(int idRegistration) throws RepositoryException;
	
	public int getRegistrationLastId() throws RepositoryException;
	
	public void removeValue(float value, int idRegistration) throws RepositoryException;
	
	public void addValue(float value, int idRegistration) throws RepositoryException;
	
	public int search(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException;

}
