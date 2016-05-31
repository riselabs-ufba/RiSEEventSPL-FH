//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.exception;

public class SubmissionAlreadyInsertedException extends Exception {

	private int idSubmission;

	public SubmissionAlreadyInsertedException(int idSubmission) {
		super("Submissão já existente!");
		this.idSubmission = idSubmission;
	}

	public int getidSubmission() {
		return idSubmission;
	}
}
//#endif