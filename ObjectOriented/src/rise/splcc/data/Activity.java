
//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.data;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Activity {
	private int idActivity;
	private int idEvent;
	private int idRegistration;
	private String nameActivity;
	private String descriptionActivity;

	//solucao sera dividir os enums, contendo todas as possibilidades possiveis e colocando cc
	public enum TypeActivity{
		//#if ${ActivityMinicurso} == "T"
		MiniCurso, 
		//#endif
		//#if ${ActivityTutorial} == "T"
		Tutorial, 
		//#endif
		//#if ${ActivityPainel} == "T"
		Painel, 
		//#endif
		//#if ${ActivityWorkshop} == "T"
		Workshop,
		//#endif
		//#if ${ActivityMainTrack} == "T"
		MainTrack
		//#endif
	}
	
	private float value;
	private float hourlyLoad;
	private String date;
	private String hour;
	private int numberOfParticipants;
	private int registrationLimit;
	private TypeActivity typeActivity;
	
	public TypeActivity getTypeActivity() {
		return typeActivity;
	}
	public void setTypeActivity(TypeActivity typeActivity) {
		this.typeActivity = typeActivity;
	}
	
	
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getNameActivity() {
		return nameActivity;
	}
	public void setNameActivity(String nameActivity) {
		this.nameActivity = nameActivity;
	}
	public String getDescriptionActivity() {
		return descriptionActivity;
	}
	public void setDescriptionActivity(String descriptionActivity) {
		this.descriptionActivity = descriptionActivity;
	}
	public int getNumberOfParticipants() {
		return numberOfParticipants;
	}
	public void setNumberOfParticipants(int numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}
	public int getRegistrationLimit() {
		return registrationLimit;
	}
	public void setRegistrationLimit(int registrationLimit) {
		this.registrationLimit = registrationLimit;
	}
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public int getIdRegistration() {
		return idRegistration;
	}
	public void setIdRegistration(int idRegistration) {
		this.idRegistration = idRegistration;
	}

	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getHourlyLoad() {
		return hourlyLoad;
	}
	public void setHourlyLoad(float hourlyLoad) {
		this.hourlyLoad = hourlyLoad;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString(){
		return "Activity Id:"+ idActivity + "\nName:" + nameActivity + "\nDescription:" + descriptionActivity + "\nType:" + typeActivity.toString() + "\nValeu:" + value + "\nHourly Load:" + hourlyLoad + "\nDate:" + date + "\nHour:" + hour+ "\nN Of Part.::" + numberOfParticipants+ "\nReg. Limit:" + registrationLimit;
	}
	//#if ${ReportsFrequencyperActivity} == "T"
	public void frequencyPerActivity(List<String> ParticipantsPerActivity, String eventName) throws DocumentException, IOException{
        Document documento = new Document();
        String nomePasta = "doc_frequencia";
        if (!new File(nomePasta).exists()) { // Verifica se o diretório existe.   
            (new File(nomePasta)).mkdir();   // Cria o diretório   
        }
        File arquivo = new File(nomePasta + "\\" + ".Frequencia_" + nameActivity +".pdf");
        String nomeArquivoCertificado = arquivo.getPath();
            OutputStream outputStream;
			outputStream = new FileOutputStream(nomeArquivoCertificado, true);
            PdfWriter.getInstance(documento, outputStream);
            //documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquiv
            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(0, 0, 255)); //classe lowagie
            Paragraph cabecalho = new Paragraph(eventName, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);   
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            Date date = new Date();
            font = new Font(Font.TIMES_ROMAN, 18, Font.BOLD); //classe lowagie
            Paragraph paragrafo = new Paragraph(nameActivity + " \ndia - " + dateFormat.format(date), font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
            for (int i = 0; i < ParticipantsPerActivity.size(); i++) {
                font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
                int tamString = ParticipantsPerActivity.get(i).length();
                int qntUnsderLine = 50 - tamString;
                String underLi = "_";
                for (int j = 1; j < qntUnsderLine; j++) {
                    underLi = underLi + "_";
                }
                Paragraph paragrafo2 = new Paragraph(ParticipantsPerActivity.get(i) + underLi, font);
                paragrafo2.setAlignment(Element.ALIGN_LEFT);
                paragrafo2.setSpacingAfter(20);
                documento.add(paragrafo2);
            }
            documento.close();
            outputStream.close();
            outputStream.flush();
	}
	//#endif
	//#if ${ReportsListofAuthors} == "T"
	public void listOfAuthorsPerActivity(Set<String> authorsPerActivity) throws DocumentException, IOException{
		
		List<String> authors = new ArrayList<String>(authorsPerActivity);
        Document documento = new Document();
        String nomePasta = "doc_frequencia";
        if (!new File(nomePasta).exists()) { // Verifica se o diretório existe.   
            (new File(nomePasta)).mkdir();   // Cria o diretório   
        }
        File arquivo = new File(nomePasta + "\\" + ".ListOfAuthors" + nameActivity +".pdf");
        String nomeArquivoCertificado = arquivo.getPath();
       
            OutputStream outputStream;
			outputStream = new FileOutputStream(nomeArquivoCertificado, true);
			
            PdfWriter.getInstance(documento, outputStream);
            //documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquivo

            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(0, 0, 255)); //classe lowagie
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            Date date = new Date();;
              

            font = new Font(Font.TIMES_ROMAN, 18, Font.BOLD); //classe lowagie
            Paragraph paragrafo = new Paragraph(nameActivity + " \ndia - " + dateFormat.format(date), font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);

            for (int i = 0; i < authors.size(); i++) {
                font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
                int tamString = authors.get(i).length();
                int qntUnsderLine = 50 - tamString;
                String underLi = "_";
                for (int j = 1; j < qntUnsderLine; j++) {
                    underLi = underLi + "_";
                }
                Paragraph paragrafo2 = new Paragraph(authors.get(i) + underLi, font);
                paragrafo2.setAlignment(Element.ALIGN_LEFT);
                paragrafo2.setSpacingAfter(20);
                documento.add(paragrafo2);
            }
            documento.close();
            outputStream.close();
            outputStream.flush();
	}
	//#endif
}
//#endif
