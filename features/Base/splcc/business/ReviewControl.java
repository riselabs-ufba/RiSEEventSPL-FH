//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import rise.splcc.data.Review;
import rise.splcc.data.User;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewNotFoundException;
import rise.splcc.repository.ReviewRepository;
import rise.splcc.util.Email;


public class ReviewControl {
	
    private ReviewRepository reviews;
	
	public ReviewControl(ReviewRepository repository){
		this.reviews = repository;
	}

	
	public void insert(Review review) throws ReviewAlreadyInsertedException, RepositoryException{
		if (review != null) {
			if (!reviews.isThere(review.getIdReview())) 
				reviews.insert(review);
			else
				throw new ReviewAlreadyInsertedException(review.getIdReview());
		} else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idReview) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		reviews.remove(idReview);
	}
	
	public void update(Review review) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		reviews.update(review);
	}
	
	
	public List<Review> getReviews() throws RepositoryException {
		return reviews.getReviews();  
	}
	
	public int getReviewLastId() throws RepositoryException{
		return reviews.getReviewLastId();
	}
	
	public Review search(int idReview) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		return reviews.search(idReview);
	}

	public boolean isThere(int idReview) throws RepositoryException {
		return reviews.isThere(idReview);
	}
	
	public void emailRoundNotification(Review review, User user, Email email) throws EmailException{
		email.sendRoundNotification(review, user);
	}
	
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException{
		return reviews.getReviewsBySubmission(idSubmission);
	}
}
//#endif