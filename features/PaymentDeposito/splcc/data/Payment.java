package rise.splcc.data;

public class Payment {
	
	public enum TypePayment{
		original();
		
		//#if ${PaymentDeposito} == "T"
		Deposito
		//#endif
	}
	
	public void startarAcaoTypePayment(Payment payment) throws DocumentException, IOException{
		//#if ${PaymentDeposito} == "T"
		if(payment.paymentType.equals("Deposito")){
			JFileChooser local = new JFileChooser();  
			local.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	        local.setDialogTitle("Escolha um local para salvar o Carne");  
	        
	        local.setFileHidingEnabled(false);
	        int res = local.showSaveDialog(null);  
	        
	        if (res == JFileChooser.APPROVE_OPTION) {
				String caminho = String.valueOf(local.getSelectedFile());
				gerarCarne(payment);
	        }
		}
		//#endif
	}
	
	
	//#if ${PaymentDeposito} == "T"
	public boolean gerarCarne(Payment payment) {
        Document documento = new Document();
     
        try {
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
            Paragraph paragrafo2 = new Paragraph("Este boleto pode ser pago em qualquer agencia bancaria: "
                    + payment.barcode + " \n\n valor " + payment.value + "\n\n Apos o prazo de 5 dias somente podera ser pago no Banco do Brasil"
                    , font);
            paragrafo2.setAlignment(Element.ALIGN_JUSTIFIED);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);
            
            System.out.println("Carne criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Carne criado com sucesso!", null,
					JOptionPane.INFORMATION_MESSAGE);
            
            documento.close();
            outputStream.close();
            outputStream.flush();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (com.lowagie.text.DocumentException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
	//#endif

}
