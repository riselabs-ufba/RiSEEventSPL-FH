//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.exception;

public class SubmissionUserAlreadyInsertedException extends Exception {

	private int idSubmissionUser;

	public SubmissionUserAlreadyInsertedException(int idSubmissionUser) {
		super("A inclusao do usuario na Submissão já existente!");
		this.idSubmissionUser = idSubmissionUser;
	}

	public int getidSubmissionUser() {
		return idSubmissionUser;
	}
}
//#endif