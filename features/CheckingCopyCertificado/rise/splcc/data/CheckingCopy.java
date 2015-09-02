package rise.splcc.data;

public class CheckingCopy {

	public enum TypeCheckingCopy{
		original();
		//#if ${CheckingCopyCertificado} == "T"
		Certificado
		//#endif
	}
	
	//#if ${CheckingCopyCertificado} == "T"
	public boolean emitirCertificado(String nome, String evento, String periodo, String atividade) throws DocumentException, IOException {
        Document documento = new Document();
       
            OutputStream outputStream = new FileOutputStream(nome + "_Atestado", true);
            PdfWriter.getInstance(documento, outputStream);
            documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquivo

            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(120, 100, 200)); //classe lowagie
            Paragraph cabecalho = new Paragraph(evento, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);

            font = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, new Color(120, 200, 200)); //classe lowagie
            Paragraph paragrafo = new Paragraph("Certificado", font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);

            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo2 = new Paragraph("Certificamos que", font);
            paragrafo2.setAlignment(Element.ALIGN_CENTER);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);

            font = new Font(Font.TIMES_ROMAN, 22, Font.BOLD); //classe lowagie
            Paragraph paragrafo3 = new Paragraph(nome, font);
            paragrafo3.setAlignment(Element.ALIGN_CENTER);
            paragrafo3.setSpacingAfter(20);
            documento.add(paragrafo3);

            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo4 = new Paragraph(getTexto(evento, periodo, atividade), font);
            paragrafo4.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(paragrafo4);
            
            System.out.println("Certificado criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Certificado criado com sucesso!", null,
					JOptionPane.INFORMATION_MESSAGE);
            
            documento.close();
            outputStream.close();
            outputStream.flush();           
            return true;
    }
	
	 private String getTexto(String evento, String periodo, String atividade) {
		 String lista_minicursos = "\n\n";
		 String texto = "";
		 if (atividade.equals("MiniCurso")){
			 texto = "Participou de um minicurso " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
	            texto += lista_minicursos;
		 }
		 
		 if (atividade.equals("Palestra")){
			 texto = "Participou de uma Palestra " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
	            texto += lista_minicursos;
		 }
		 
		 if (atividade.equals("Tutorial")){
			 texto = "Participou de um Tutorial " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
	            texto += lista_minicursos;
		 }
		 
		 if (atividade.equals("Painel")){
			 texto = "Participou de um Painel " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
	            texto += lista_minicursos;
		 }
		 return texto;
	 }
	//#endif	 
}
