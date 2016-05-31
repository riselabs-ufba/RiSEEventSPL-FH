
package rise.splcc.data;

public class Activity {

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
            documento.open();//abre o arquivo

            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(0, 0, 255)); //classe lowagie
            Paragraph cabecalho = new Paragraph(eventName, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            Date date = new Date();;
              

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
}
//#endif
