//#if ${Speaker} == "T"
package rise.splcc.exception;

public class SpeakerNotFoundException extends Exception {

    private int idSpeaker;

    public SpeakerNotFoundException(int idSpeaker){
        super ("Speaker não encontrado!");
        this.idSpeaker = idSpeaker;
    }

    public int getidSpeaker(){
        return idSpeaker;
    }

}
//#endif