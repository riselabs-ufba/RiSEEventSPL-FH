package rise.splcc.data;

public class Payment {
	
	public enum TypePayment{
		original();
		//#if ${PaymentAvista} == "T"
		Avista, 
		//#endif
	}
	
	
	
	public void startarAcaoTypePayment(Payment payment) throws DocumentException, IOException{
		original();
		//#if ${PaymentAvista} == "T"
		if(payment.paymentType.equals("Avista")){
			
			JFileChooser local = new JFileChooser();  
			local.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	        local.setDialogTitle("Escolha um local para salvar o Boleto");  
	        
	        local.setFileHidingEnabled(false);
	        int res = local.showSaveDialog(null);  
	        
	        if (res == JFileChooser.APPROVE_OPTION) {
				String caminho = String.valueOf(local.getSelectedFile());
				gerarBoleto(payment);
	        }
		}
		//#endif
	}
	
	//#if ${PaymentAvista} == "T"
	public boolean gerarBoleto(Payment payment) throws DocumentException, IOException {
        Document documento = new Document();
     
            OutputStream outputStream = new FileOutputStream(payment.idPayment + "_Boleto", true);
            PdfWriter.getInstance(documento, outputStream);
            documento.open();//abre o arquivo
            //adiciona o texto
           
            Font font = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, new Color(120, 200, 200)); //classe lowagie
            Paragraph paragrafo = new Paragraph("Boleto", font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo2 = new Paragraph("Declaramos para os devidos fins de direito, que o pagamento de numero"
                    + payment.idPayment + " codigo de barra " + payment.barcode + " valor " + payment.value + "relativo ao numero de registro em evento "
                    + payment.idRegistration + " foi pago na data " + payment.date, font);
            paragrafo2.setAlignment(Element.ALIGN_JUSTIFIED);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);
            
            System.out.println("Boleto criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Boleto criado com sucesso!", null,
					JOptionPane.INFORMATION_MESSAGE);
            
            documento.close();
            outputStream.close();
            outputStream.flush();
            return true;
    }
	//#endif
}
