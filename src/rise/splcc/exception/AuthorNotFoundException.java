//#if ${InsertAuthors} == "T"
package rise.splcc.exception;

public class AuthorNotFoundException extends Exception {

    private int idAuthor;

    public AuthorNotFoundException(int idAuthor){
        super ("Author não encontrado!");
        this.idAuthor = idAuthor;
    }

    public int getidAuthor(){
        return idAuthor;
    }

}
//#endif