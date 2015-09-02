package rise.splcc.data;

public class Payment {
	
	public enum TypePayment{
		original();
		//#if ${PaymentCartao} == "T"
		Credito,
		//#endif
	}
	
	public void startarAcaoTypePayment(Payment payment) throws DocumentException, IOException{
		original();
		
		//#if ${PaymentCartao} == "T"
		if(payment.paymentType.equals("Credito")){
			JOptionPane.showInputDialog("Digite o numero do cartao");
			JOptionPane.showInputDialog("Digite o numero de seguranca");
			JOptionPane.showInputDialog("Digite o nome do titular do cartao");
			System.out.println("Transacao enviada para validacao do Banco!");
            JOptionPane.showMessageDialog(null, "Apos 24, o comprovante de pagamento poderar ser solicitado!", null,
					JOptionPane.INFORMATION_MESSAGE);
		}
		//#endif
	}
}
