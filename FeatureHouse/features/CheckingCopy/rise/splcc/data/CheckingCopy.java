//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.data;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class CheckingCopy {

	
	
	private int idCheckingCopy;
	private int idUser;
	private int idRegistration;
	private String dateOfIssue;
	
	
	public int getIdCheckingCopy() {
		return idCheckingCopy;
	}
	public void setIdCheckingCopy(int idCheckingCopy) {
		this.idCheckingCopy = idCheckingCopy;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdRegistration() {
		return idRegistration;
	}
	public void setIdRegistration(int idRegistration) {
		this.idRegistration = idRegistration;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	
	public String toString(){
		return "CheckingCopy Id:"+ idCheckingCopy + "\nRegistration Id:" + idRegistration + "\nUser Id:" + idUser + "\nType:" + checkingCopyType.toString() + "\nDate of Issue:" + dateOfIssue;
	}
	

}
//#endif
