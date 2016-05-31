package rise.splcc.facade;

public class RiSEventFacade {

	
	//#if ${CheckingCopyCertificado} == "T"
	public void emitirCertificado (String nome, String evento, String periodo, String atividade, CheckingCopy checkingcopy) throws RepositoryException, DocumentException, IOException {
		checkingCopys.emitirCertificado(nome, evento, periodo, atividade, checkingcopy);
	}
	//#endif
}
