//#if ${InsertAuthors} == "T"
package rise.splcc.data;

public class SubmissionAuthor {

	
	private int idAuthor;
	private int idActivity;
	private int idSubmission;
	
	
	public int getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public int getIdSubmission() {
		return idSubmission;
	}
	public void setIdSubmission(int idSubmission) {
		this.idSubmission = idSubmission;
	}
	
	
}
//#endif