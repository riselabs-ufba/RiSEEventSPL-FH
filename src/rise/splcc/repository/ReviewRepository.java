//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Review;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewNotFoundException;


public interface ReviewRepository {

	public void insert(Review review) throws RepositoryException;
	
	public List<Review> getReviews() throws RepositoryException;
	
	public boolean isThere(int idReview) throws RepositoryException;
	
	public int getReviewLastId() throws RepositoryException;
	
	public void remove(int idReview) throws ReviewNotFoundException, RepositoryException;
	
	public void update(Review review) throws ReviewNotFoundException, RepositoryException;
	
	public Review search(int idReview) throws ReviewNotFoundException, RepositoryException;
	
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException;
	
}
//#endif