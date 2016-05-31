//#if ${Speaker} == "T"
package rise.splcc.exception;

public class SpeakerAlreadyInsertedException extends Exception {

	private int idUser;

	public SpeakerAlreadyInsertedException(int idUser) {
		super("Speaker já existente!");
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
	
	public String toString(){
		return "Speaker já cadastrado!";
	}
}
//#endif