
package rise.splcc.business;

public class UserControl {

	//#if ${Bugs} == "T"
	public String sendBug(String nome, String assunto, String mensagem, Email email) throws EmailException{
		return email.sendBugtrackEmail(nome, assunto, mensagem);
	}
	//#endif
}
