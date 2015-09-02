package rise.splcc.facade;

public class RiSEventFacade {
	
	//#if ${Bugs} == "T"
	public String sendBug(String nome, String assunto, String mensagem, Email email) throws EmailException {
		return users.sendBug(nome, assunto, mensagem, email);
	}
	//#endif
}
