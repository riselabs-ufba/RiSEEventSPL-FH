package rise.splcc.business;

import java.util.List;

import rise.splcc.data.Registration;
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.RegistrationRepository;


public class RegistrationControl {
	
    private RegistrationRepository registrations;
	
	public RegistrationControl(RegistrationRepository repository){
		this.registrations = repository;
	}

	public List<Registration> getRegistrations() throws RepositoryException {
		return registrations.getRegistrations();  
	}
	
	public Registration search(int idRegistration) throws RegistrationAlreadyInsertedException, RepositoryException, RegistrationNotFoundException{
		return registrations.search(idRegistration);
	}
	
	public void insert(Registration registration) throws RegistrationAlreadyInsertedException, RepositoryException{
		if (registration != null) {
            if (!registrations.isThere(registration.getIdRegistration())) {
                registrations.insert(registration);
            } else {
                throw new RegistrationAlreadyInsertedException(registration.getIdRegistration());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(int idRegistration) throws RegistrationAlreadyInsertedException, RepositoryException, RegistrationNotFoundException{
		registrations.remove(idRegistration);
	}
	
	public void update(Registration registration) throws RegistrationAlreadyInsertedException, RepositoryException, RegistrationNotFoundException{
		registrations.update(registration);
	}

	public boolean isThere(int idRegistration) throws RepositoryException {
		return registrations.isThere(idRegistration);
	}
	
	public int getRegistrationLastId() throws RepositoryException{
		return registrations.getRegistrationLastId();
	}
	
	public void removeValue(float value, int idRegistration) throws RepositoryException{
		registrations.removeValue(value, idRegistration);
	}
	
	public void addValue(float value, int idRegistration) throws RepositoryException{
		registrations.addValue(value, idRegistration);
	}
	
	public int search(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException{
		return registrations.search(idUser, idEvent);
	}

}
