//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.data;

public class SubmissionUser {

	private int idUser;
	private int idActivity;
	private int idSubmission;
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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