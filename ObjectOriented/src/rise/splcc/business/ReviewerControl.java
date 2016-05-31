//#if ${Reviewer} == "T"
package rise.splcc.business;

import java.util.List;

import rise.splcc.data.Reviewer;
import rise.splcc.data.User;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewerAlreadyInsertedException;
import rise.splcc.exception.ReviewerNotFoundException;
import rise.splcc.repository.ReviewerRepository;

public class ReviewerControl {

	private ReviewerRepository reviewers;
	
	public ReviewerControl(ReviewerRepository repository){
		this.reviewers = repository;
	}
	
	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.
	public void insert(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException{
		if (reviewer != null) {
			if (!reviewers.isThere(reviewer.getIdUser())) 
				reviewers.insert(reviewer);
			else
				throw new ReviewerAlreadyInsertedException(reviewer.getIdUser());
		} else {
            throw new IllegalArgumentException();
        }
	}  

	public void remove(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewers.isThere(idUser))
			reviewers.remove(idUser);
		else
			throw new ReviewerNotFoundException(idUser);
	}
	
	public void update(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewers.isThere(reviewer.getIdUser()))
			reviewers.update(reviewer);
		else
			throw new ReviewerNotFoundException(reviewer.getIdUser());
	}

	public Reviewer search(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		return reviewers.search(idUser);
	}

	public boolean isThere(int idUser) throws RepositoryException {
		return reviewers.isThere(idUser);
	}

	public List<Reviewer> getReviewers() throws RepositoryException { 
		return reviewers.getReviewers();  
	}
	
	public Reviewer getReviewerByknowledgeArea(String knowledgearea) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		return reviewers.getReviewerByknowledgeArea(knowledgearea);
	}

}
//#endif