//#if ${Reviewer} == "T"
package rise.splcc.exception;

public class ReviewerNotFoundException extends Exception {

    private int idReviewer;

    public ReviewerNotFoundException(int idReviewer){
        super ("Reviewer não encontrado!");
        this.idReviewer = idReviewer;
    }

    public int getidReviewer(){
        return idReviewer;
    }

}
//#endif