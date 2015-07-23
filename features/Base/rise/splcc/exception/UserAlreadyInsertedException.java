
package rise.splcc.exception;

public class UserAlreadyInsertedException extends Exception {

	private int idUser;

	public UserAlreadyInsertedException(int idUser) {
		super("User já existente!");
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
	
	public String toString(){
		return "User já existente!";
	}
}
