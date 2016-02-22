//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.exception;

public class SubmissionNotFoundException extends Exception {

    private int idSubmission;

    public SubmissionNotFoundException(int idSubmission){
        super ("Submissão não encontrada!");
        this.idSubmission = idSubmission;
    }

    public int getidSubmission(){
        return idSubmission;
    }

}
//#endif