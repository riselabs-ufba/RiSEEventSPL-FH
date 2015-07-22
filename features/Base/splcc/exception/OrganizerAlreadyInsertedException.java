//#if ${Organizer} == "T"
package rise.splcc.exception;

public class OrganizerAlreadyInsertedException extends Exception {

	private int idUser;

	public OrganizerAlreadyInsertedException(int idUser) {
		super("Organizer já existente!");
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
}
//#endif