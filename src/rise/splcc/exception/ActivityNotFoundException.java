//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.exception;

public class ActivityNotFoundException extends Exception {

    private int idActivity;

    public ActivityNotFoundException(int idActivity){
        super ("Activity não encontrado!");
        this.idActivity = idActivity;
    }

    public int getidActivity(){
        return idActivity;
    }

}
//#endif