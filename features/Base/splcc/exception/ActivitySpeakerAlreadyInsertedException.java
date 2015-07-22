//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.exception;

public class ActivitySpeakerAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivitySpeakerAlreadyInsertedException(int idActivity) {
		super("a inclusão do speaker na atividade já existente!");
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif