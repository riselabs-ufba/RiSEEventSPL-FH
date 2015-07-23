
package rise.splcc.exception;

public class UserNotFoundException extends Exception {

    private int idUser;

    public UserNotFoundException(int idUser){
        super ("Usuario não encontrado!");
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}
