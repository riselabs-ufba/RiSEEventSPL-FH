package rise.splcc.data;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Event {
	
	//#if ${ReportsFrequencyperEvent} == "T"
	public void frequencyPerEvent(List<String> ParticipantsPerEvent) throws DocumentException, IOException{
        Document documento = new Document();
        String nomePasta = "doc_frequencia";
        if (!new File(nomePasta).exists()) { // Verifica se o diretório existe.   
            (new File(nomePasta)).mkdir();   // Cria o diretório   
        }
        File arquivo = new File(nomePasta + "\\" + ".Frequencia_" + eventName +".pdf");
        String nomeArquivoCertificado = arquivo.getPath();
       
            OutputStream outputStream;
			outputStream = new FileOutputStream(nomeArquivoCertificado, true);
			
            PdfWriter.getInstance(documento, outputStream);
            //documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquivo
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            Date date = new Date();;

            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(0, 0, 255)); //classe lowagie
            Paragraph cabecalho = new Paragraph(eventName + " \ndia - " + dateFormat.format(date), font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);

            for (int i = 0; i < ParticipantsPerEvent.size(); i++) {
                font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
                int tamString = ParticipantsPerEvent.get(i).length();
                int qntUnsderLine = 50 - tamString;
                String underLi = "_";
                for (int j = 1; j < qntUnsderLine; j++) {
                    underLi = underLi + "_";
                }
                Paragraph paragrafo2 = new Paragraph(ParticipantsPerEvent.get(i) + underLi, font);
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

