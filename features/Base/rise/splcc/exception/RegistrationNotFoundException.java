package rise.splcc.exception;

public class RegistrationNotFoundException extends Exception {

    private int idRegistration;

    public RegistrationNotFoundException(int idRegistration){
        super ("Registration não encontrado!");
        this.idRegistration = idRegistration;
    }

    public int getidRegistration(){
        return idRegistration;
    }

}
