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
	
	public enum StatusPayment{
		Parcial, Completo, Incompleto
	}

	private int idPayment;
	private int idRegistration;

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
	}
	
}
//#endif
