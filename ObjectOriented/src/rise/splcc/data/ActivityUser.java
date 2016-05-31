//#if ${RegistrationUserActivity} == "T"
package rise.splcc.data;

public class ActivityUser {
	private int idUser;
	private int idActivity;
	private float frequency;
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public float getFrequency() {
		return frequency;
	}
	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}
	
	
}
//#endif