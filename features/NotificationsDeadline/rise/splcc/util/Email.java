package rise.splcc.util;

import rise.splcc.data.Review;
import rise.splcc.data.User;

public class Email {

	public void sendNotification(User user, Review review) throws EmailException{
		original(user, review);
		
		//#if ${NotificationsDeadline} == "T"
		// esta classe eh chamada logo apos o insert do assignment
		String assunto = "Prazo de entrega de Rivisao";
		String mensagem = "O prazo de entrega é ate 15 dias antes da data oficial de Inicio do evento. Seu ID para cadastrar a revisao é:" + review.getIdReview() + " Favor usar este ID!" ;
		String emailDestino = user.getEmail();
		//#endif
	
		//#if ${NotificationsDeadline} == "T"
		//deadline email
		SimpleEmail email = new SimpleEmail(); 
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo(emailDestino, user.getNameUser()); //destinat�rio 
		email.setFrom("riseeventemail@gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("riseeventemail@gmail.com", "senhasecreta");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		email.send(); //envia o e-mail
		//#endif
		
	}
}
