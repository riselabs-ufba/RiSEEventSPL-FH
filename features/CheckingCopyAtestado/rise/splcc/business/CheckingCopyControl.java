package rise.splcc.business;

public class CheckingCopyControl {
	
	//#if ${CheckingCopyAtestado} == "T"
	public void emitirAtestado(String nome, String evento, String periodo, CheckingCopy checkingcopy) throws RepositoryException{
		checkingcopy.emitirAtestado(nome, evento, periodo);
	}
	//#endif
	
}
