package rise.splcc.business;

public class CheckingCopyControl {
	
	//#if ${CheckingCopyCertificado} == "T"
	public void emitirCertificado(String nome, String evento, String periodo, String atividade, CheckingCopy checkingcopy) throws RepositoryException, DocumentException, IOException{
		checkingcopy.emitirCertificado(nome, evento, periodo, atividade);
	}
	//#endif
}
