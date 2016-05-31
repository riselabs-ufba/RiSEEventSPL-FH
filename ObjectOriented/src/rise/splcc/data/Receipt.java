//#if ${Receipt} == "T"
package rise.splcc.data;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.ui2.RiSEEventMainScreenP;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Receipt {
	
	private int idReceipt;
	private int idPayment;
	private float totalValue;
	private String dateOfIssue;
	private Blob attachment;
	
	
	public int getIdReceipt() {
		return idReceipt;
	}
	public void setIdReceipt(int idReceipt) {
		this.idReceipt = idReceipt;
	}
	public int getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
	public float getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public Blob getAttachment() {
		return attachment;
	}
	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}
	
	public String toString(){
		return "Receipt Id:"+ idReceipt + "\nPayment Id:" + idPayment + "\nTotal Value:" + totalValue + "\nDate of Issue:" + dateOfIssue;
	}
	
	//Esta funcao dece receber um payment relativo ao receipt que foi gerado (buscar o payment pelo idpaymente de receipt)
	public void gerarRecibo(Payment payment) throws IOException, DocumentException{
		JFileChooser local = new JFileChooser();  
		local.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
        local.setDialogTitle("Escolha um local para salvar o Recibo");  
        
        local.setFileHidingEnabled(false);
        int res = local.showSaveDialog(null);  
        
        if (res == JFileChooser.APPROVE_OPTION) {
			String caminho = String.valueOf(local.getSelectedFile());
			criarDocumento(payment);
        }
	}

	
	public void criarDocumento(Payment payment) throws IOException, DocumentException {
        Document documento = new Document();
     
            OutputStream outputStream = new FileOutputStream(payment.getIdPayment() + "_Recibo", true);
            PdfWriter.getInstance(documento, outputStream);
            documento.open();//abre o arquivo
            //adiciona o texto
           
            Font font = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, new Color(120, 200, 200)); //classe lowagie
            Paragraph paragrafo = new Paragraph("RECIBO", font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo2 = new Paragraph("Declaramos para os devidos fins de direito, que o pagamento de numero"
                    + payment.getIdPayment() + " codigo de barra " + payment.getBarcode() + " valor " + payment.getValue() + "relativo ao numero de registro em evento "
                    + payment.getIdRegistration() + " foi pago na data " + payment.getDate(), font);
            paragrafo2.setAlignment(Element.ALIGN_JUSTIFIED);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);
            
            System.out.println("Boleto criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Boleto criado com sucesso!", null,
					JOptionPane.INFORMATION_MESSAGE);
            
            documento.close();
            outputStream.close();
            outputStream.flush();
    }
	
	
}
//#endif