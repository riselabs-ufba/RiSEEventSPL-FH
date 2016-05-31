//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.data;

import java.util.ArrayList;
import java.util.List;



public class Assignment {
	private int idReviwerUser;
	private int idReview;
	private int idReviewSubmission;
	private String date;
	
	public int getIdReview() {
		return idReview;
	}
	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdReviwerUser() {
		return idReviwerUser;
	}
	public void setIdReviwerUser(int idReviwerUser) {
		this.idReviwerUser = idReviwerUser;
	}
	public int getIdReviewSubmission() {
		return idReviewSubmission;
	}
	public void setIdReviewSubmission(int idReviewSubmission) {
		this.idReviewSubmission = idReviewSubmission;
	}
	
	public String toString(){
		return "Review Id:"+ idReview + "\nReviewer User Id:" + idReviwerUser + "\nReview Submission Id:" + idReviewSubmission + "\nDate:" + date;
	}
	
	
	//a lista de submissao que a funcao recebe eh uma lista do getSubmissions(todas elas)
//	public List<Submission> automaticoPreferenceIndication(Reviewer reviewer, List<Submission> submissions){
//		List<Submission> submissions2 = new ArrayList<Submission>();
//
//		
//		for(Submission s : submissions){
//			String keyword = s.getKeywords();
//			String knowledgeArea = reviewer.getKnowledgeArea();
//			if(knowledgeArea.equals(keyword)){
//				submissions2.add(s);
//				return submissions2;
//			}
//		}
//		return submissions;
//	}
	
}
//#endif