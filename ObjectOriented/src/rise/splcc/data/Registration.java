package rise.splcc.data;

public class Registration {
	private int idRegistration;
	private int idUser;
	private int idEvent;
	private float totalValue;
	
	
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public int getIdRegistration() {
		return idRegistration;
	}
	public void setIdRegistration(int idRegistration) {
		this.idRegistration = idRegistration;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public float getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}
	
	public String toString(){
		return "Registration Id:"+ idRegistration + "\nUser Id:" + idUser + "\nTotal Value:" + totalValue;
	}
	
}
