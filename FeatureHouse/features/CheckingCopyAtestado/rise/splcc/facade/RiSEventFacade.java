package rise.splcc.facade;

public class RiSEventFacade {

	//#if ${CheckingCopyAtestado} == "T"
	public void emitirAtestado (String nome, String evento, String periodo, CheckingCopy checkingcopy) throws RepositoryException {
		checkingCopys.emitirAtestado(nome, evento, periodo, checkingcopy);
	}
	//#endif
	
}
