package rise.splcc.facade;

//#if ${Reviewer} == "T"
import rise.splcc.business.ReviewerControl;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.data.Reviewer;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.exception.ReviewerAlreadyInsertedException;
import rise.splcc.exception.ReviewerNotFoundException;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.repository.ReviewerRepository;
import rise.splcc.repository.ReviewerRepositoryBDR;
//#endif

public class RiSEventFacade {

	//#if ${Reviewer} == "T"
	private ReviewerControl reviewers;
	//#endif

	public void init(){
		
		original();
		
		//#if ${Reviewer} == "T"
		ReviewerRepository reviewerRepository = ReviewerRepositoryBDR.getInstance();
		//#endif
		
		//#if ${Reviewer} == "T"
		reviewers = new ReviewerControl(reviewerRepository);
		//#endif
	}
	
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
}
