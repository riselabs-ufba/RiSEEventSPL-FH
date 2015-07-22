//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.data;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Payment {
	
	public enum TypePayment{
		//#if ${PaymentAvista} == "T"
		Avista, 
		//#endif
		//#if ${PaymentCartao} == "T"
		Credito,
		//#endif
		//#if ${PaymentDeposito} == "T"
		Deposito
		//#endif
	}
	
	public enum StatusPayment{
		Parcial, Completo, Incompleto
	}

	private int idPayment;
	private int idRegistration;
	private TypePayment paymentType;
	private StatusPayment status;
	private String date;
	private float value;
	private Blob attachment;
	private String barcode;
	
	
	public int getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
	public int getIdRegistration() {
		return idRegistration;
	}
	public void setIdRegistration(int idRegistration) {
		this.idRegistration = idRegistration;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Blob getAttachment() {
		return attachment;
	}
	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public TypePayment getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(TypePayment paymentType) {
		this.paymentType = paymentType;
	}
	public StatusPayment getStatus() {
		return status;
	}
	public void setStatus(StatusPayment status) {
		this.status = status;
	}
	public float  getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	public String toString(){
		return "Payment Id:"+ idPayment + "\nRegistration Id:" + idRegistration + "\nType:" + paymentType.toString() + "\nStatus:" + status.toString() + "\nDate:" + date + "\nValue:" + value + "\nBarcode:" + barcode;
	}
	
	public void startarAcaoTypePayment(Payment payment) throws DocumentException, IOException{
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
//#endif