package rise.splcc.util;


public class Email {

	public void sendNotification(User user, Review review) throws EmailException{
		original(user, review);
	
//		//#if ${NotificationsAceptanceRejection} == "T"
//		String assunto2 = "Resultado Revisao Papper!";
//		String mensagem2 = "Seu Papper esta sendo revisado. O resultado sera encaminhado via email.";
//		String emailDestino2 = author.getEmail();
//		//#endif
		
		
//		//#if ${NotificationsAceptanceRejection} == "T"
//		// AcceptReject email
//		SimpleEmail email2 = new SimpleEmail(); 
//		email2.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
//		
//		email2.addTo(emailDestino2, user.getNameUser()); //destinat�rio 
//		email2.setFrom("riseeventemail@gmail.com", "Gerenciador de Eventos Rise"); // remetente 
//		email2.setSubject(assunto2); // assunto do e-mail 
//		email2.setMsg(mensagem2); //conteudo do e-mail
//		
//		email2.setAuthentication("riseeventemail@gmail.com", "senhasecreta");
//		email2.setSslSmtpPort( "465" ); //578 ou 465
//		email2.setSSLOnConnect(true);
//		email2.setStartTLSEnabled(true);
//		email2.setStartTLSRequired(true);
//		
//		email2.send(); //envia o e-mail
//		//#endif
		
	}
	
}
