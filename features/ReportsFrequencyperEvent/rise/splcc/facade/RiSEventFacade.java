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
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.business.CheckingCopyControl;
//#endif
import rise.splcc.business.EventControl;
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
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.data.CheckingCopy;
//#endif
import rise.splcc.data.Event;
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
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.exception.CheckingCopyAlreadyInsertedException;
import rise.splcc.exception.CheckingCopyNotFoundException;
//#endif
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
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
//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
import rise.splcc.repository.CheckingCopyRepository;
import rise.splcc.repository.CheckingCopyRepositoryBDR;
//#endif
import rise.splcc.repository.EventRepository;
import rise.splcc.repository.EventRepositoryBDR;
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

	//#if ${ReportsFrequencyperEvent} == "T"
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException{
		return events.getParticipantsPerEvent(idEvent);
	}
	//#endif
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public void frequencyPerEvent(List<String> ParticipantsPerEvent, Event event) throws DocumentException, IOException{
		events.frequencyPerEvent(ParticipantsPerEvent, event);
	}
	//#endif
}
