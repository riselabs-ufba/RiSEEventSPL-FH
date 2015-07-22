//#if ${InsertAuthors} == "T"
package rise.splcc.exception;

public class SubmissionAuthorAlreadyInsertedException extends Exception {

	private int idSubmissionAuthor;

	public SubmissionAuthorAlreadyInsertedException(int idSubmissionAuthor) {
		super("A inclusao do author na Submissão já existente!");
		this.idSubmissionAuthor = idSubmissionAuthor;
	}

	public int getidSubmissionAuthor() {
		return idSubmissionAuthor;
	}
}
//#endif