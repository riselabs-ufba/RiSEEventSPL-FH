//#if ${Organizer} == "T"
package rise.splcc.exception;

public class OrganizerNotFoundException extends Exception {

    private int idUser;

    public OrganizerNotFoundException(int idUser){
        super ("Organizer não encontrado!");
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}
//#endif