package rise.splcc.exception;

public class EventNotFoundException extends Exception {

    private int idEvent;

    public EventNotFoundException(int idEvent){
        super ("evento não encontrado!");
        this.idEvent = idEvent;
    }

    public int getidEvent(){
        return idEvent;
    }

}
