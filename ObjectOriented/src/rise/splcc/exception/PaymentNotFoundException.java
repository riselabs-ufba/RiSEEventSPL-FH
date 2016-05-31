//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.exception;

public class PaymentNotFoundException extends Exception {

    private int idPayment;

    public PaymentNotFoundException(int idPayment){
        super ("Payment não encontrado!");
        this.idPayment = idPayment;
    }

    public int getidPayment(){
        return idPayment;
    }

}
//#endif