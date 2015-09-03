package rise.splcc.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.mail.EmailException;

import rise.splcc.business.ActivityControl;
import rise.splcc.business.ActivityUserControl;
import rise.splcc.business.EventControl;
import rise.splcc.business.RegistrationControl;
import rise.splcc.business.UserControl;
import rise.splcc.data.Activity;
import rise.splcc.data.ActivityUser;
import rise.splcc.data.Event;
import rise.splcc.data.Registration;
import rise.splcc.data.User;
import rise.splcc.exception.ActivityUserAlreadyInsertedException;
import rise.splcc.exception.ActivityUserNotFoundException;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;
import rise.splcc.repository.ActivityRepository;
import rise.splcc.repository.ActivityRepositoryBDR;
import rise.splcc.repository.ActivityUserRepository;
import rise.splcc.repository.ActivityUserRepositoryBDR;
import rise.splcc.repository.EventRepository;
import rise.splcc.repository.EventRepositoryBDR;
import rise.splcc.repository.RegistrationRepository;
import rise.splcc.repository.RegistrationRepositoryBDR;
import rise.splcc.repository.UserRepository;
import rise.splcc.repository.UserRepositoryBDR;
import rise.splcc.util.Email;

import com.lowagie.text.DocumentException;


public class RiSEventFacade {

	private EventControl events;

	private UserControl users;

	private RegistrationControl registrations;

	protected static RiSEventFacade instance;
	
	public RiSEventFacade(){
		init();
	}
	
	
	public void init(){
		
		EventRepository eventRepository = EventRepositoryBDR.getInstance();
		
		UserRepository userRepository = UserRepositoryBDR.getInstance();
		
		RegistrationRepository registrationRepository = RegistrationRepositoryBDR.getInstance();

		events = new EventControl(eventRepository);
		
		users = new UserControl(userRepository);
		
		registrations = new RegistrationControl(registrationRepository);
	}
	
	public static RiSEventFacade getInstance()  {
		if (RiSEventFacade.instance == null) {
			RiSEventFacade.instance = new RiSEventFacade();
		}
		return RiSEventFacade.instance;
	}
	
	//EVENT
	
	public void insertEvent(Event event) throws EventAlreadyInsertedException, RepositoryException{
		this.events.insert(event);
	}
	
	public void removeEvent(int idEvent) throws EventNotFoundException, RepositoryException, EventAlreadyInsertedException{
		events.remove(idEvent);  
	}
	
	public void updateEvent(Event event) throws EventNotFoundException, RepositoryException, EventAlreadyInsertedException{
		events.update(event);
	}
	
	public List<Event> getEvents() throws RepositoryException{
		return events.getEvents();
	}
	
	public Event searchEvent(int idEvent) throws EventNotFoundException, RepositoryException, EventAlreadyInsertedException{
		return events.search(idEvent);
	}
	
	public int getEventLastId() throws RepositoryException{
		return events.getEventLastId();
	}
	
	public int getEventIdByName(String eventName) throws RepositoryException{
		return events.getEventIdByName(eventName);
	}
	
	
	//USER
	public void insertUser(User user) throws UserAlreadyInsertedException, RepositoryException{
		this.users.insert(user);
	}
	
	public void removeUser(int idUser) throws UserNotFoundException, RepositoryException, UserAlreadyInsertedException{
		users.remove(idUser);  
	}
	
	public void updateUser(User user) throws UserNotFoundException, RepositoryException, UserAlreadyInsertedException{
		users.update(user);
	}
	
	public List<User> getUsers() throws RepositoryException{
		return users.getUsers();
	}
	
	public User searchUser(int idUser) throws UserNotFoundException, RepositoryException, UserAlreadyInsertedException{
		return users.search(idUser);
	}
	
	public int getUserIdByName(String userName) throws RepositoryException{
		return users.getUserIdByName(userName);
	}
	
	
	//REGISTRATION
	public List<Registration> getRegistrations() throws RepositoryException{
		return registrations.getRegistrations();
	}
	
	public Registration searchRegistration(int idRegistration) throws RegistrationNotFoundException, RepositoryException, RegistrationAlreadyInsertedException{
		return registrations.search(idRegistration);
	}
	
	public void insertRegistration(Registration registration) throws RegistrationAlreadyInsertedException, RepositoryException{
		registrations.insert(registration);
	}

	public void removeRegistration(int idRegistration) throws RegistrationNotFoundException, RepositoryException, RegistrationAlreadyInsertedException{
		registrations.remove(idRegistration);  
	}

	public void updateRegistration(Registration registration) throws RegistrationNotFoundException, RepositoryException, RegistrationAlreadyInsertedException{
		registrations.update(registration);
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
	
	public int searchRegistration(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException{
		return registrations.search(idUser, idEvent);
	}
}
