//#if ${Receipt} == "T" 
package rise.splcc.exception;

public class ReceiptNotFoundException extends Exception {

    private int idReceipt;

    public ReceiptNotFoundException(int idReceipt){
        super ("Receipt não encontrado!");
        this.idReceipt = idReceipt;
    }

    public int getidReceipt(){
        return idReceipt;
    }

}
//#endif