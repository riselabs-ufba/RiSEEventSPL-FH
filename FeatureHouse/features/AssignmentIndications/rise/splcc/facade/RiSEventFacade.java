package rise.splcc.facade;

//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.business.AssignmentControl;
//#endif
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.data.Assignment;
//#endif
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.exception.AssignmentAlreadyInsertedException;
import rise.splcc.exception.AssignmentNotFoundException;
//#endif
//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
import rise.splcc.repository.AssignmentRepository;
import rise.splcc.repository.AssignmentRepositoryBDR;
//#endif


public class RiSEventFacade {


	//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T" 
	private AssignmentControl assignments;
	//#endif
	
	
	public void init(){
		
		original();
		
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		AssignmentRepository assignmentRepository = AssignmentRepositoryBDR.getInstance();
		//#endif
		
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		assignments = new AssignmentControl(assignmentRepository);
		//#endif
		
	}
	
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
