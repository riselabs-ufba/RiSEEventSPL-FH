//#if ${InsertAuthors} == "T"
package rise.splcc.exception;

public class AuthorAlreadyInsertedException extends Exception {

	private int idAuthor;

	public AuthorAlreadyInsertedException(int idAuthor) {
		super("Author já existente!");
		this.idAuthor = idAuthor;
	}

	public int getidAuthor() {
		return idAuthor;
	}
}
//#endif