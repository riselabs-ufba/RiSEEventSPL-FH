//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.exception;

public class ActivitySpeakerNotFoundException extends Exception {

    private int idActivity;

    public ActivitySpeakerNotFoundException(int idActivity){
        super ("Inclusão do speaker na activity não encontrado!");
        this.idActivity = idActivity;
    }

    public int getidActivity(){
        return idActivity;
    }

}
//#endif