package rise.splcc.data;

public class CheckingCopy {

	public enum TypeCheckingCopy{
		//#if ${CheckingCopyAtestado} == "T"
		Atestado
		//#endif
	}
	
	private TypeCheckingCopy checkingCopyType;
	
	public TypeCheckingCopy getCheckingCopyType() {
		return checkingCopyType;
	}
	public void setCheckingCopyType(TypeCheckingCopy checkingCopyType) {
		this.checkingCopyType = checkingCopyType;
	}
	
	//#if ${CheckingCopyAtestado} == "T"
	public boolean emitirAtestado(String nome, String evento, String periodo) {
        Document documento = new Document();
     
        try {
            OutputStream outputStream = new FileOutputStream(nome + "_Atestado", true);
            PdfWriter.getInstance(documento, outputStream);
            documento.open();//abre o arquivo
            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(120, 100, 200)); //classe lowagie
            Paragraph cabecalho = new Paragraph(evento, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);
            font = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, new Color(120, 200, 200)); //classe lowagie
            Paragraph paragrafo = new Paragraph("Atestado", font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo2 = new Paragraph("Declaramos para os devidos fins de direito, que "
                    + nome + " esteve presente nas atividades deste evento no dia "
                    + periodo + "", font);
            paragrafo2.setAlignment(Element.ALIGN_JUSTIFIED);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);
            
            System.out.println("Atestado criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Atestado criado com sucesso!", null,
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
