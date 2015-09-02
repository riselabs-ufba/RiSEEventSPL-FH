package rise.splcc.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.mail.EmailException;

import rise.splcc.business.ActivityControl;
import rise.splcc.business.ActivityUserControl;
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.business.AssignmentControl;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.business.AuthorControl;
//#endif
import rise.splcc.business.EventControl;
import rise.splcc.business.RegistrationControl;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.business.ReviewControl;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.business.SubmissionAuthorControl;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.business.SubmissionControl;
import rise.splcc.business.SubmissionUserControl;
//#endif
import rise.splcc.business.UserControl;
import rise.splcc.data.Activity;
import rise.splcc.data.ActivityUser;
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.data.Assignment;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.data.Author;
//#endif
import rise.splcc.data.Event;
import rise.splcc.data.Registration;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.data.Review;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.data.Submission;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.data.SubmissionAuthor;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.data.SubmissionUser;
//#endif
import rise.splcc.data.User;
import rise.splcc.exception.ActivityUserAlreadyInsertedException;
import rise.splcc.exception.ActivityUserNotFoundException;
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.exception.AssignmentAlreadyInsertedException;
import rise.splcc.exception.AssignmentNotFoundException;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.AuthorAlreadyInsertedException;
import rise.splcc.exception.AuthorNotFoundException;
//#endif
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewNotFoundException;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.exception.SubmissionAlreadyInsertedException;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.SubmissionAuthorAlreadyInsertedException;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.exception.SubmissionNotFoundException;
import rise.splcc.exception.SubmissionUserAlreadyInsertedException;
//#endif
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;
import rise.splcc.repository.ActivityRepository;
import rise.splcc.repository.ActivityRepositoryBDR;
import rise.splcc.repository.ActivityUserRepository;
import rise.splcc.repository.ActivityUserRepositoryBDR;
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.repository.AssignmentRepository;
import rise.splcc.repository.AssignmentRepositoryBDR;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.repository.AuthorRepository;
import rise.splcc.repository.AuthorRepositoryBDR;
//#endif
import rise.splcc.repository.EventRepository;
import rise.splcc.repository.EventRepositoryBDR;
import rise.splcc.repository.RegistrationRepository;
import rise.splcc.repository.RegistrationRepositoryBDR;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.repository.ReviewRepository;
import rise.splcc.repository.ReviewRepositoryBDR;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.repository.SubmissionAuthorRepository;
import rise.splcc.repository.SubmissionAuthorRepositoryBDR;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.repository.SubmissionRepository;
import rise.splcc.repository.SubmissionRepositoryBDR;

import rise.splcc.repository.SubmissionUserRepository;
import rise.splcc.repository.SubmissionUserRepositoryBDR;
//#endif
import rise.splcc.repository.UserRepository;
import rise.splcc.repository.UserRepositoryBDR;
import rise.splcc.util.Email;

import com.lowagie.text.DocumentException;


public class RiSEventFacade {

	private EventControl events;

	private UserControl users;

	private RegistrationControl registrations;
	//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
	private ReviewControl reviews;
	//#endif
	//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
	private SubmissionControl submissions;
	//#endif
	//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
	private SubmissionUserControl submissionUsers;
	//#endif
	//#if ${InsertAuthors} == "T"
	private SubmissionAuthorControl submissionAuthors;
	//#endif
	//#if ${InsertAuthors} == "T"
	private AuthorControl authors;
	//#endif
	//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T" 
	private AssignmentControl assignments;
	//#endif

	protected static RiSEventFacade instance;
	
	public RiSEventFacade(){
		init();
	}
	
	
	public void init(){
		
		EventRepository eventRepository = EventRepositoryBDR.getInstance();
		
		UserRepository userRepository = UserRepositoryBDR.getInstance();
		
		RegistrationRepository registrationRepository = RegistrationRepositoryBDR.getInstance();
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		ReviewRepository reviewRepository = ReviewRepositoryBDR.getInstance();
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		SubmissionRepository submissionRepository = SubmissionRepositoryBDR.getInstance();
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		SubmissionUserRepository submissionUserRepository = SubmissionUserRepositoryBDR.getInstance();
		//#endif
		//#if ${InsertAuthors} == "T"
		SubmissionAuthorRepository submissionAuthorRepository = SubmissionAuthorRepositoryBDR.getInstance();
		//#endif
		//#if ${InsertAuthors} == "T"
		AuthorRepository authorRepository = AuthorRepositoryBDR.getInstance();
		//#endif
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		AssignmentRepository assignmentRepository = AssignmentRepositoryBDR.getInstance();
		//#endif
		
		events = new EventControl(eventRepository);
		
		users = new UserControl(userRepository);
		
		registrations = new RegistrationControl(registrationRepository);
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		reviews = new ReviewControl(reviewRepository);
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		submissions = new SubmissionControl(submissionRepository);
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		submissionUsers = new SubmissionUserControl(submissionUserRepository);
		//#endif
		//#if ${InsertAuthors} == "T"
		submissionAuthors = new SubmissionAuthorControl(submissionAuthorRepository);
		//#endif
		//#if ${InsertAuthors} == "T"
		authors = new AuthorControl(authorRepository);
		//#endif
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		assignments = new AssignmentControl(assignmentRepository);
		//#endif
		
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
	
	
	//REVIEW
	//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
	public void insertReview(Review review) throws RepositoryException, ReviewAlreadyInsertedException{
		this.reviews.insert(review);
	}
	
	public List<Review> getReviews() throws RepositoryException{
		return reviews.getReviews();
	}
	
	public int getReviewLastId() throws RepositoryException{
		return reviews.getReviewLastId();
	}
	
	public void removeReview(int idReview) throws ReviewNotFoundException, RepositoryException, ReviewAlreadyInsertedException{
		reviews.remove(idReview);  
	}
	
	public void updateReview(Review review) throws ReviewNotFoundException, RepositoryException, ReviewAlreadyInsertedException{
		reviews.update(review);
	}
	
	public Review searchReview(int idReview) throws ReviewNotFoundException, RepositoryException, ReviewAlreadyInsertedException{
		return reviews.search(idReview);
	}
	
	public boolean isThereReview(int idReview) throws RepositoryException{
		return reviews.isThere(idReview);
	}
	
	public void emailRoundNotification (Review review, User user, Email email) throws EmailException{
		reviews.emailRoundNotification(review, user, email);
	}
	
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException{
		return reviews.getReviewsBySubmission(idSubmission);
	}
	//#endif
	//SUBMISSION
	//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
	public void insertSubmission(Submission submission) throws RepositoryException, SubmissionAlreadyInsertedException{
		this.submissions.insert(submission);
	}
	
	public List<Submission> getSubmissions() throws RepositoryException{
		return submissions.getSubmissions();
	}
	
	public int getSubmissionLastId() throws RepositoryException{
		return submissions.getSubmissionLastId();
	}
	
	public void removeSubmission(int idSubmission) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		submissions.remove(idSubmission);  
	}
	
	public void updateSubmission(Submission submission) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		submissions.update(submission);
	}
	
	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException{
		return submissions.getSubmissionIdByTitle(submissionTitle);
	}
	
	public Submission searchSubmission(int idSubmission) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		return submissions.search(idSubmission);
	}
	//#if ${SubmissionCompleta} == "T"
	public void insertAttachment(File attachment, int idActivity) throws RepositoryException, SubmissionAlreadyInsertedException{
		this.submissions.inserAttachmanet(attachment, idActivity);
	}
	//#endif
	public void pdfRecovey(int idSubmission) throws RepositoryException{
		this.submissions.pdfRecover(idSubmission);
	}
	
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException{
		return submissions.getSubmissionsByUser(idUser);
	}
	
	//SUBMISSIONUSER
	
	public void insertSubmissionUser(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException{
		submissionUsers.insert(submissionUser);
	}
	
	public List<SubmissionUser> getSubmissionUsers() throws RepositoryException{
		return submissionUsers.getSubmissionUsers();
	}
	//#endif
	
	//SUBMISSIONAUTHOR
	//#if ${InsertAuthors} == "T"
	public void insertSubmissionAuthor(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException{
		submissionAuthors.insert(submissionAuthor);
	}
	
	public List<SubmissionAuthor> getSubmissionAuthors() throws RepositoryException{
		return submissionAuthors.getSubmissionAuthors();
	}
	
	public boolean isThereSubmissionAuthor(SubmissionAuthor submissionauthor) throws RepositoryException{
		return submissionAuthors.isThere(submissionauthor);
	}
	//#endif
	
	//AUTHOR
	//#if ${InsertAuthors} == "T"
	public void insertAuthor(Author author) throws AuthorAlreadyInsertedException, RepositoryException{
		authors.insert(author);
	}

	public void removeAuthor(int idAuthor) throws AuthorNotFoundException, RepositoryException, AuthorAlreadyInsertedException{
		authors.remove(idAuthor);  
	}

	public void updateAuthor(Author author) throws AuthorNotFoundException, RepositoryException, AuthorAlreadyInsertedException{
		authors.update(author);
	}

	public List<Author> getAuthors() throws RepositoryException{
		return authors.getAuthors();
	}

	public Author searchAuthor(int idAuthor) throws AuthorNotFoundException, RepositoryException, AuthorAlreadyInsertedException{
		return authors.search(idAuthor);
	} 
	public int getAuthorLastId() throws RepositoryException{
		return authors.getAuthorLastId();
		
	}
	//#endif
	//ASSIGNMENT
	//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
	public void insertAssignment(Assignment review) throws RepositoryException, AssignmentAlreadyInsertedException{
		this.assignments.insert(review);
	}
	
	public void removeAssignment(Assignment assignment) throws AssignmentNotFoundException, RepositoryException, AssignmentAlreadyInsertedException{
		assignments.remove(assignment);  
	}
	
	public Assignment searchAssignment(Assignment assignment) throws AssignmentNotFoundException, RepositoryException, AssignmentAlreadyInsertedException{
		return assignments.search(assignment);
	}
	
	public List<Assignment> getAssignments() throws RepositoryException{
		return assignments.getAssignments();
	}
	
	public void updateAssignment(Assignment assignment) throws AssignmentNotFoundException, RepositoryException, AssignmentAlreadyInsertedException{
		assignments.update(assignment);
	}
	
	public boolean isThereAssignment(Assignment assignment) throws RepositoryException{
		return assignments.isThere(assignment);
	}
	//#endif
}
