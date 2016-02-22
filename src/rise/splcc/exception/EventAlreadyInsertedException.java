package rise.splcc.exception;

public class EventAlreadyInsertedException extends Exception {

	private int idEvent;

	public EventAlreadyInsertedException(int idEvent) {
		super("Evento já existente!");
		this.idEvent = idEvent;
	}

	public int getidEvent() {
		return idEvent;
	}
}