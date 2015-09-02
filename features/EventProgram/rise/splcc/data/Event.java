package rise.splcc.data;

public class Event {

	//#if ${EventProgram} == "T"
	public void generateProgram(List<Activity> activities) throws DocumentException, IOException{
        Document documento = new Document();
        String nomePasta = "Event Program";
        if (!new File(nomePasta).exists()) { // Verifica se o diretório existe.   
            (new File(nomePasta)).mkdir();   // Cria o diretório   
        }
        File arquivo = new File(nomePasta + "\\" + ".EventProgram_" + eventName +".pdf");
        String nomeArquivoCertificado = arquivo.getPath();
       
            OutputStream outputStream;
			outputStream = new FileOutputStream(nomeArquivoCertificado, true);
			
            PdfWriter.getInstance(documento, outputStream);
            //documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquivo
            


            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(0, 0, 255)); //classe lowagie
            Paragraph cabecalho = new Paragraph("Programa do evento:" + eventName, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);
            
            Font font2 = new Font(Font.TIMES_ROMAN, 20, Font.BOLD);
            Paragraph paragrafo2 = new Paragraph("Name:       " + " " + "Type:   " + " " + "Date:    " + " " + "Hour:    " , font2);
            paragrafo2.setAlignment(Element.ALIGN_LEFT);
            paragrafo2.setSpacingAfter(5);
            documento.add(paragrafo2);

            for (int i = 0; i < activities.size(); i++) {
                font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
                int tamString = activities.get(i).getNameActivity().length();
                int qntUnsderLine = 50 - tamString;
                
                Paragraph paragrafo3 = new Paragraph(activities.get(i).getNameActivity()+ " " + activities.get(i).getTypeActivity() + " " + activities.get(i).getDate() + " " + activities.get(i).getHour(), font);
                paragrafo3.setAlignment(Element.ALIGN_LEFT);
                paragrafo3.setSpacingAfter(20);
                documento.add(paragrafo3);
            }
            documento.close();
            outputStream.close();
            outputStream.flush();

	}
	//#endif
	
}

