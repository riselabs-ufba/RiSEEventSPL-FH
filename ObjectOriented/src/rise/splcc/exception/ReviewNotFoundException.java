//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.exception;

public class ReviewNotFoundException extends Exception {

    private int idReview;

    public ReviewNotFoundException(int idReview){
        super ("Revisao não encontrada!");
        this.idReview = idReview;
    }

    public int getidReview(){
        return idReview;
    }

}
//#endif