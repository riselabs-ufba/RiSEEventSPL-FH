//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.business;

import java.util.List;

import org.apache.commons.mail.EmailException;


import rise.splcc.data.Assignment;


import rise.splcc.data.Review;
import rise.splcc.data.User;
import rise.splcc.exception.AssignmentAlreadyInsertedException;
import rise.splcc.exception.AssignmentNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.repository.AssignmentRepository;
import rise.splcc.util.Email;

public class AssignmentControl {
	
    private AssignmentRepository assignments;
	
	public AssignmentControl(AssignmentRepository repository){
		this.assignments = repository;
	}
	
	public void insert(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException{
		if (assignment != null) {
            if (!assignments.isThere(assignment)) {
                assignments.insert(assignment);
            } else {
                throw new AssignmentAlreadyInsertedException(assignment.getIdReview());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		assignments.remove(assignment);
	}
	
	public void update(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		assignments.update(assignment);
	}
	
	public Assignment search(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		return assignments.search(assignment);
	}

	public boolean isThere(Assignment assignment) throws RepositoryException {
		return assignments.isThere(assignment);
	}

	public List<Assignment> getAssignments() throws RepositoryException {
		return assignments.getAssignments();  
	}
	//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"	
	public void emailNotification(User user, Review review, Email email) throws EmailException{
		email.sendNotification(user, review);
	}
	//#endif
}
//#endif