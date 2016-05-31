//#if ${RegistrationOrganizerActivity} == "T"
package rise.splcc.exception;

public class ActivityOrganizerNotFoundException extends Exception {

    private int idActivity;

    public ActivityOrganizerNotFoundException(int idActivity){
        super ("Inclusão do organizer na activity não encontrado!");
        this.idActivity = idActivity;
    }

    public int getidActivity(){
        return idActivity;
    }

}
//#endif