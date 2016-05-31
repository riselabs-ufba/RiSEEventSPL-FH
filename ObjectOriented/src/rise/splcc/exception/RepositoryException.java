package rise.splcc.exception;
public class RepositoryException extends Exception {

    private Exception exception;

    public RepositoryException(Exception exception) {
        super("Exceção encapsulada");
        this.exception = exception;
    }

    public String getMessage() {
        return exception.getMessage();
    }

    public void printStackTrace() {
        exception.printStackTrace();
    }

    public String toString(){
    	return "Erro no Repositorio!!";
    }
}