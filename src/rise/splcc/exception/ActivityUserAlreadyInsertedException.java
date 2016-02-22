//#if ${RegistrationUserActivity} == "T"
package rise.splcc.exception;

public class ActivityUserAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivityUserAlreadyInsertedException(int idActivity) {
		super("a inclusão do user na atividade já existente!");
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif