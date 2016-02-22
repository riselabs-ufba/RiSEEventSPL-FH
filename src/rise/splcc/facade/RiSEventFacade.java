package rise.splcc.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.mail.EmailException;

import rise.splcc.business.ActivityControl;
//#if ${RegistrationOrganizerActivity} == "T"
import rise.splcc.business.ActivityOrganizerControl;
//#endif
//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.business.ActivitySpeakerControl;
//#endif
import rise.splcc.business.ActivityUserControl;
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.business.AssignmentControl;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.business.AuthorControl;
//#endif
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.business.CheckingCopyControl;
//#endif
import rise.splcc.business.EventControl;
//#if ${Organizer} == "T"
import rise.splcc.business.OrganizerControl;
//#endif
//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.business.PaymentControl;
//#endif
//#if ${Receipt} == "T"
import rise.splcc.business.ReceiptControl;
//#endif
import rise.splcc.business.RegistrationControl;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.business.ReviewControl;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.business.ReviewerControl;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.business.SpeakerControl;
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
//#if ${RegistrationOrganizerActivity} == "T"
import rise.splcc.data.ActivityOrganizer;
//#endif
//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.data.ActivitySpeaker;
//#endif
import rise.splcc.data.ActivityUser;
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.data.Assignment;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.data.Author;
//#endif
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.data.CheckingCopy;
//#endif
import rise.splcc.data.Event;
//#if ${Organizer} == "T"
import rise.splcc.data.Organizer;
//#endif
//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.data.Payment;
//#endif
//#if ${Receipt} == "T"
import rise.splcc.data.Receipt;
//#endif
import rise.splcc.data.Registration;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.data.Review;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.data.Reviewer;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.data.Speaker;
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
//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
//#endif
//#if ${RegistrationOrganizerActivity} == "T"
import rise.splcc.exception.ActivityOrganizerAlreadyInsertedException;
import rise.splcc.exception.ActivityOrganizerNotFoundException;
//#endif
//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.exception.ActivitySpeakerAlreadyInsertedException;
import rise.splcc.exception.ActivitySpeakerNotFoundException;
//#endif
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
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.exception.CheckingCopyAlreadyInsertedException;
import rise.splcc.exception.CheckingCopyNotFoundException;
//#endif
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
//#if ${Organizer} == "T"
import rise.splcc.exception.OrganizerAlreadyInsertedException;
import rise.splcc.exception.OrganizerNotFoundException;
//#endif
//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
//#endif
//#if ${Receipt} == "T"
import rise.splcc.exception.ReceiptAlreadyInsertedException;
import rise.splcc.exception.ReceiptNotFoundException;
//#endif
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewNotFoundException;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.exception.ReviewerAlreadyInsertedException;
import rise.splcc.exception.ReviewerNotFoundException;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.exception.SpeakerAlreadyInsertedException;
import rise.splcc.exception.SpeakerNotFoundException;
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
//#if ${RegistrationOrganizerActivity} == "T"
import rise.splcc.repository.ActivityOrganizerRepository;
import rise.splcc.repository.ActivityOrganizerRepositoryBDR;
//#endif
import rise.splcc.repository.ActivityRepository;
import rise.splcc.repository.ActivityRepositoryBDR;
//#if ${RegistrationSpeakerActivity} == "T"
import rise.splcc.repository.ActivitySpeakerRepository;
import rise.splcc.repository.ActivitySpeakerRepositoryBDR;
//#endif
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
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.repository.CheckingCopyRepository;
import rise.splcc.repository.CheckingCopyRepositoryBDR;
//#endif
import rise.splcc.repository.EventRepository;
import rise.splcc.repository.EventRepositoryBDR;
//#if ${Organizer} == "T"
import rise.splcc.repository.OrganizerRepository;
import rise.splcc.repository.OrganizerRepositoryBDR;
//#endif
//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
import rise.splcc.repository.PaymentRepository;
import rise.splcc.repository.PaymentRepositoryBDR;
//#endif
//#if ${Receipt} == "T"
import rise.splcc.repository.ReceiptRepository;
import rise.splcc.repository.ReceiptRepositoryBDR;
//#endif
import rise.splcc.repository.RegistrationRepository;
import rise.splcc.repository.RegistrationRepositoryBDR;
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.repository.ReviewRepository;
import rise.splcc.repository.ReviewRepositoryBDR;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.repository.ReviewerRepository;
import rise.splcc.repository.ReviewerRepositoryBDR;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.repository.SpeakerRepository;
import rise.splcc.repository.SpeakerRepositoryBDR;
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

	//#if ${Speaker} == "T"
	private SpeakerControl speakers;
	//#endif
	//#if ${Organizer} == "T"
	private OrganizerControl organizers;
	//#endif
	//#if ${Reviewer} == "T"
	private ReviewerControl reviewers;
	//#endif
	//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
	private ActivityControl activities;
	//#endif
	//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
	private PaymentControl payments;
	//#endif
	private RegistrationControl registrations;
	//#if ${RegistrationSpeakerActivity} == "T"
	private ActivitySpeakerControl activitySpeakers;
	//#endif
	//#if ${RegistrationUserActivity} == "T"
	private ActivityUserControl activityUsers;
	//#endif
	//#if ${RegistrationOrganizerActivity} == "T"
	private ActivityOrganizerControl activityOrganizers;
	//#endif
	//#if ${Receipt} == "T"
	private ReceiptControl receipts;
	//#endif
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
	//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
	private CheckingCopyControl checkingCopys;
	//#endif
	//#if ${InsertAuthors} == "T"
	private AuthorControl authors;
	//#endif
	//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T" 
	private AssignmentControl assignments;
	//#endif

	protected static RiSEventFacade instance;
	
	public RiSEventFacade(){
		EventRepository eventRepository = EventRepositoryBDR.getInstance();
		
		UserRepository userRepository = UserRepositoryBDR.getInstance();
		
		//#if ${Speaker} == "T"
		SpeakerRepository speakerRepository = SpeakerRepositoryBDR.getInstance();
		//#endif
		//#if ${Organizer} == "T"
		OrganizerRepository organizerRepository = OrganizerRepositoryBDR.getInstance();
		//#endif
		//#if ${Reviewer} == "T"
		ReviewerRepository reviewerRepository = ReviewerRepositoryBDR.getInstance();
		//#endif
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		ActivityRepository activityRepository = ActivityRepositoryBDR.getInstance();
		//#endif
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		PaymentRepository paymentRepository = PaymentRepositoryBDR.getInstance();
		//#endif
		RegistrationRepository registrationRepository = RegistrationRepositoryBDR.getInstance();
		//#if ${RegistrationSpeakerActivity} == "T"
		ActivitySpeakerRepository activitySpeakerRepository = ActivitySpeakerRepositoryBDR.getInstance();
		//#endif
		//#if ${RegistrationUserActivity} == "T"
		ActivityUserRepository activityUserRepository = ActivityUserRepositoryBDR.getInstance();
		//#endif
		//#if ${RegistrationOrganizerActivity} == "T"
		ActivityOrganizerRepository activityOrganizerRepository = ActivityOrganizerRepositoryBDR.getInstance();
		//#endif
		//#if ${Receipt} == "T"
		ReceiptRepository receiptRepository = ReceiptRepositoryBDR.getInstance();
		//#endif
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
		//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		CheckingCopyRepository checkingCopyRepository = CheckingCopyRepositoryBDR.getInstance();
		//#endif
		//#if ${InsertAuthors} == "T"
		AuthorRepository authorRepository = AuthorRepositoryBDR.getInstance();
		//#endif
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		AssignmentRepository assignmentRepository = AssignmentRepositoryBDR.getInstance();
		//#endif
		
		events = new EventControl(eventRepository);
		
		users = new UserControl(userRepository);
		
		//#if ${Speaker} == "T"
		speakers = new SpeakerControl(speakerRepository);
		//#endif
		//#if ${Organizer} == "T"
		organizers = new OrganizerControl(organizerRepository);
		//#endif
		//#if ${Reviewer} == "T"
		reviewers = new ReviewerControl(reviewerRepository);
		//#endif
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		activities = new ActivityControl(activityRepository);
		//#endif
		//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
		payments = new PaymentControl(paymentRepository);
		//#endif
		registrations = new RegistrationControl(registrationRepository);
		//#if ${RegistrationSpeakerActivity} == "T"
		activitySpeakers = new ActivitySpeakerControl(activitySpeakerRepository);
		//#endif
		//#if ${RegistrationUserActivity} == "T"
		activityUsers = new ActivityUserControl(activityUserRepository);
		//#endif
		//#if ${RegistrationOrganizerActivity} == "T"
		activityOrganizers = new ActivityOrganizerControl(activityOrganizerRepository);
		//#endif
		//#if ${Receipt} == "T"
		receipts = new ReceiptControl(receiptRepository);
		//#endif
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
		//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		checkingCopys = new CheckingCopyControl(checkingCopyRepository);
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
	
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException{
		return events.getParticipantsPerEvent(idEvent);
	}
	//#endif
	//#if ${EventProgram} == "T"
	public void generateProgram (List<Activity> activities, Event event) throws DocumentException, IOException{
		events.generateProgram(activities, event);
	}
	//#endif
	
	//#if ${EventImportantDates} == "T"
	public void generateImportantDates(String abstractDate, String fullPaperDate, String notificationDate, Event event) throws DocumentException, IOException{
		events.generateImportantDates(abstractDate, fullPaperDate, notificationDate, event);
	}
	//#endif
	//#if ${ReportsFrequencyperEvent} == "T"
	public void frequencyPerEvent(List<String> ParticipantsPerEvent, Event event) throws DocumentException, IOException{
		events.frequencyPerEvent(ParticipantsPerEvent, event);
	}
	//#endif
	
	
	
	
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
	//#if ${Bugs} == "T"
	public String sendBug(String nome, String assunto, String mensagem, Email email) throws EmailException {
		return users.sendBug(nome, assunto, mensagem, email);
	}
	//#endif
	
	
	//SPEAKER
	//#if ${Speaker} == "T"
	public void insertSpeaker(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException{
		speakers.insert(speaker);
	}

	public void removeSpeaker(int idUser) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		speakers.remove(idUser);  
	}

	public void updateSpeaker(Speaker speaker) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		speakers.update(speaker);
	}

	public List<Speaker> getSpeakers() throws RepositoryException{
		return speakers.getSpeakers();
	}

	public Speaker searchSpeaker(int idUser) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		return speakers.search(idUser);
	} 
	
	public boolean isThereSpeaker(int idUser) throws RepositoryException{
		return speakers.isThere(idUser);
	}
	//#endif
	//REVIEWER
	//#if ${Reviewer} == "T"
	public void insertReviewer(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException{
		reviewers.insert(reviewer);
	}

	public void removeReviewer(int idUser) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		reviewers.remove(idUser);  
	}

	public void updateReviewer(Reviewer reviewer) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		reviewers.update(reviewer);
	}

	public List<Reviewer> getReviewers() throws RepositoryException{
		return reviewers.getReviewers();
	}

	public Reviewer searchReviewer(int idUser) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		return reviewers.search(idUser);
	} 
	
	public boolean isThereReviewer(int idUser) throws RepositoryException{
		return reviewers.isThere(idUser);
	}
	
	public Reviewer getReviewerByknowledgeArea(String knowledgearea) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		return reviewers.getReviewerByknowledgeArea(knowledgearea);
	}
	//#endif
	
	//ACTIVITY
	//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
	public void insertActivity(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException{
		activities.insert(activity);
	}

	public void removeActivity(int idActivity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		activities.remove(idActivity);  
	}

	public void updateActivity(Activity activity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		activities.update(activity);
	}

	public List<Activity> getActivities() throws RepositoryException{
		return activities.getActivities();
	}

	public Activity searchActivity(int idActivity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		return activities.search(idActivity);
	} 
	
	public int getActivityLastId() throws RepositoryException{
		return activities.getActivityLastId();
	}
	
	public int getActivityIdByName(String activityName) throws RepositoryException{
		return activities.getActivityIdByName(activityName);
	}
	
	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException{
		return activities.getActivitiesByEvent(idEvent);
	}
	
	public float getEventMainTrackValue(int idEvent) throws RepositoryException{
		return activities.getEventMainTrackValue(idEvent);
	}
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException{
		return activities.getActivityMainTrackId(idEvent);
	}
	
	public int getEventbyActivity(int idActivity) throws RepositoryException{
		return activities.getEventbyActivity(idActivity);
	}
	
	//#if ${ReportsFrequencyperActivity} == "T"
	public void frequencyPerActivity(List<String> ParticipantsPerActivity, Activity activity, String eventName) throws DocumentException, IOException{
		activities.frequencyPerActivity(ParticipantsPerActivity, activity, eventName);
	}
	//#endif
	
	//#if ${ReportsListofAuthors} == "T"
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException{
		return activities.getListOfAuthorsPerActivity(idActivity);
	}
	
	public void listOfAuthorsPerActivity(Set<String> authorsPerActivity, Activity activity) throws DocumentException, IOException{
		activity.listOfAuthorsPerActivity(authorsPerActivity);
	}
	//#endif
	//#endif
	
	
	
	//ORGANIZER
	//#if ${Organizer} == "T"
	public void insertOrganizer(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException{
		organizers.insert(organizer);
	}

	public void removeOrganizer(int idUser) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		organizers.remove(idUser);  
	}

	public void updateOrganizer(Organizer organizer) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		organizers.update(organizer);
	}

	public List<Organizer> getOrganizers() throws RepositoryException{
		return organizers.getOrganizers();
	}

	public Organizer searchOrganizer(int idUser) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		return organizers.search(idUser);
	}
	public boolean isThereOrganizer(int idUser) throws RepositoryException{
		return organizers.isThere(idUser);
	}
	//#endif
	
	
	
	//ACTIVITYSPEAKER
	//#if ${RegistrationSpeakerActivity} == "T"
	public void insertActivitySpeaker(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException{
		activitySpeakers.insert(activitySpeaker);
	}

	public void removeActivitySpeaker(ActivitySpeaker activitySpeaker) throws ActivitySpeakerNotFoundException, RepositoryException, ActivitySpeakerAlreadyInsertedException{
		activitySpeakers.remove(activitySpeaker);  
	}
	
	public List<ActivitySpeaker> getActivitiesSpeakers() throws RepositoryException{
		return activitySpeakers.getActivitiesSpeakers();
	}
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException{
		return activitySpeakers.getActivitiesById(idActivity);
	}
	//#endif
	
	
	
	//ACTIVITYORGANIZER
	//#if ${RegistrationOrganizerActivity} == "T"
	public void insertActivityOrganizer(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException{
		activityOrganizers.insert(activityOrganizer);
	}

	public void removeActivityOrganizer(ActivityOrganizer activityOrganizer) throws ActivityOrganizerNotFoundException, RepositoryException, ActivityOrganizerAlreadyInsertedException{
		activityOrganizers.remove(activityOrganizer);  
	}

	public List<ActivityOrganizer> getActivitiesOrganizers() throws RepositoryException{
		return activityOrganizers.getActivitiesOrganizers();
	}

	public List<ActivityOrganizer> getActivitiesOrganizersById(int idActivity) throws RepositoryException{
		return activityOrganizers.getActivitiesById(idActivity);
	}
	//#endif
	
	
	
	
	//ACTIVITYUSER
	//#if ${RegistrationUserActivity} == "T"
	public void insertActivityUser(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException{
		activityUsers.insert(activityUser);
	}

	public void removeActivityUser(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException, ActivityUserAlreadyInsertedException{
		activityUsers.remove(activityUser);  
	}
	
	public List<ActivityUser> getActivitiesUsers() throws RepositoryException{
		return activityUsers.getActivitiesUsers();
	}
	
	public List<ActivityUser> getActivitiesUsersById(int idActivity) throws RepositoryException{
		return activityUsers.getActivitiesById(idActivity);
	}
	//#endif
	//#if ${ReportsFrequencyperActivity} == "T"
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		return activityUsers.getParticipantsPerActivity(idActivity);
	}
	//#endif
	
	
	
	
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
	
	
	//PAYMENT
	
	//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
	public void insertPayment(Payment payment) throws PaymentAlreadyInsertedException, RepositoryException{
		this.payments.insert(payment);
	}
	
	public List<Payment> getPayments() throws RepositoryException{
		return payments.getPayments();
	}
	
	public int getPaymentLastId() throws RepositoryException{
		return payments.getPaymentLastId();
	}
	
	public void updatePayment(Payment payment) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		payments.update(payment);
	}
	
	public void removePayment(int idPayment) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		payments.remove(idPayment);  
	}
	
	public Payment searchPayment(int idPayment) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		return payments.search(idPayment);
	}
	
	public void typePayment(Payment payment, Payment paymentout) throws DocumentException, IOException {
		this.payments.type(payment, paymentout);
	}
	//#endif
	
	
	
	//RECEIPT
	//#if ${Receipt} == "T"
	public void insertReceipt(Receipt receipt) throws ReceiptAlreadyInsertedException, RepositoryException{
		this.receipts.insert(receipt);
	}
	
	public List<Receipt> getReceipts() throws RepositoryException{
		return receipts.getReceipts();
	}
	
	public int getReceiptLastId() throws RepositoryException{
		return receipts.getReceiptLastId();
	}
	
	public void removeReceipt(int idReceipt) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		receipts.remove(idReceipt);  
	}
	
	public void updateReceipt(Receipt review) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		receipts.update(review);
	}
	
	public Receipt searchReceipt(int idReceipt) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		return receipts.search(idReceipt);
	}
	//#endif
	
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
	//CHECKINGCOPY
	
	//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
	public void insertCheckingCopy(CheckingCopy checkingCopy) throws RepositoryException, CheckingCopyAlreadyInsertedException{
		this.checkingCopys.insert(checkingCopy);
	}
	
	public List<CheckingCopy> getCheckingCopys() throws RepositoryException{
		return checkingCopys.getCheckingCopys();
	}
	
	public int getCheckingCopyLastId() throws RepositoryException{
		return checkingCopys.getCheckingCopyLastId();
	}
	
	public void removeCheckingCopy(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		checkingCopys.remove(idCheckingCopy);  
	}
	
	public void updateCheckingCopy(CheckingCopy checkingCopy) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		checkingCopys.update(checkingCopy);
	}
	
	public CheckingCopy searchCheckingCopy(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		return checkingCopys.search(idCheckingCopy);
	}
	//#if ${CheckingCopyAtestado} == "T"
	public void emitirAtestado (String nome, String evento, String periodo, CheckingCopy checkingcopy) throws RepositoryException {
		checkingCopys.emitirAtestado(nome, evento, periodo, checkingcopy);
	}
	//#endif
	
	//#if ${CheckingCopyCertificado} == "T"
	public void emitirCertificado (String nome, String evento, String periodo, String atividade, CheckingCopy checkingcopy) throws RepositoryException, DocumentException, IOException {
		checkingCopys.emitirCertificado(nome, evento, periodo, atividade, checkingcopy);
	}
	//#endif
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
	//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"
	public void emailNotification (User user, Review review, Email email) throws EmailException{
		assignments.emailNotification(user, review, email);
	}
	//#endif
	//#endif
	//REPORT
	
	
}
