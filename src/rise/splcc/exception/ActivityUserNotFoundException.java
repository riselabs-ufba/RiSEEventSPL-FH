//#if ${RegistrationUserActivity} == "T"
package rise.splcc.exception;

public class ActivityUserNotFoundException extends Exception {

    private int idUser;

    public ActivityUserNotFoundException(int idUser){
        super ("userActivity não encontrado!");
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}
//#endif