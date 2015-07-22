//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.exception;

public class ActivityAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivityAlreadyInsertedException(int idActivity) {
		super("Activity já existente!");
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif