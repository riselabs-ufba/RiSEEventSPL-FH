package rise.splcc.data;

public class Event {

	//#if ${EventImportantDates} == "T"
	public void generateImportantDates(String abstractDate, String fullPaperDate, String notificationDate) throws DocumentException, IOException{
		Document documento = new Document();
        String nomePasta = "Event Important Dates";
        if (!new File(nomePasta).exists()) { // Verifica se o diretório existe.   
            (new File(nomePasta)).mkdir();   // Cria o diretório   
        }
        File arquivo = new File(nomePasta + "\\" + ".ImportantDates_" + eventName +".pdf");
        String nomeArquivoCertificado = arquivo.getPath();
        
            OutputStream outputStream;
			outputStream = new FileOutputStream(nomeArquivoCertificado, true);
			
            PdfWriter.getInstance(documento, outputStream);
            //documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquivo
            


            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(0, 0, 255)); //classe lowagie
            Paragraph cabecalho = new Paragraph("Important Dates:" + eventName, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);
            
            Font font2 = new Font(Font.TIMES_ROMAN, 20, Font.BOLD);
            Paragraph paragrafo2 = new Paragraph("Abstracts:       " + " " + "Full Papers:   " + " " + "Notification:    " + " " + "Period:    " , font2);
            paragrafo2.setAlignment(Element.ALIGN_LEFT);
            paragrafo2.setSpacingAfter(5);
            documento.add(paragrafo2);

           
            Paragraph paragrafo3 = new Paragraph(abstractDate+ " " + fullPaperDate + " " + notificationDate + " " + this.period, font);
            paragrafo3.setAlignment(Element.ALIGN_LEFT);
            paragrafo3.setSpacingAfter(20);
            documento.add(paragrafo3);
        
            documento.close();
            outputStream.close();
            outputStream.flush();
	}
	//#endif
}

