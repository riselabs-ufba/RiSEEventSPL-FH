//#if ${Reviewer} == "T"
package rise.splcc.exception;

public class ReviewerAlreadyInsertedException extends Exception {

	private int idUser;

	public ReviewerAlreadyInsertedException(int idUser) {
		super("Reviewer já existente!");
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
}
//#endif