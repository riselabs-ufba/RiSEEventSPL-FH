//#if ${RegistrationOrganizerActivity} == "T"
package rise.splcc.exception;

public class ActivityOrganizerAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivityOrganizerAlreadyInsertedException(int idActivity) {
		super("a inclusão do organizer na atividade já existente!");
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif