
package rise.splcc.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import rise.splcc.data.User;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;
//#if ${Organizer} == "T"
import rise.splcc.repository.OrganizerRepository;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.repository.ReviewerRepository;
//#endif
import rise.splcc.repository.UserRepository;
import rise.splcc.util.Email;

public class UserControl {

	//#if ${Organizer} == "T"
	private OrganizerRepository organizers;
	//#endif
}
