//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Assignment;
import rise.splcc.exception.AssignmentNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface AssignmentRepository {

	public void insert(Assignment assignment) throws RepositoryException;

	public void remove(Assignment assignment) throws AssignmentNotFoundException, RepositoryException;
	
	public Assignment search(Assignment assignment) throws AssignmentNotFoundException, RepositoryException;
	
	public List<Assignment> getAssignments() throws RepositoryException;

	public void update(Assignment assignment) throws AssignmentNotFoundException, RepositoryException;	
	
	public boolean isThere(Assignment assignment) throws RepositoryException;
	
}
//#endif