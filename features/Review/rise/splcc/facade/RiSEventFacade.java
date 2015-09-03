package rise.splcc.facade;

//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.business.ReviewControl;
//#endif
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.data.Review;
//#endif
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewNotFoundException;
//#endif
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.repository.ReviewRepository;
import rise.splcc.repository.ReviewRepositoryBDR;
//#endif

public class RiSEventFacade {

	//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
	private ReviewControl reviews;
	//#endif

	public void init(){
		
		original();
		
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		ReviewRepository reviewRepository = ReviewRepositoryBDR.getInstance();
		//#endif
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		reviews = new ReviewControl(reviewRepository);
		//#endif
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
}
