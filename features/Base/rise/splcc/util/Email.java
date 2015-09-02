package rise.splcc.util;

import javax.swing.JOptionPane;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
//#if ${InsertAuthors} == "T"
import rise.splcc.data.Author;
//#endif
//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
import rise.splcc.data.Review;
//#endif
import rise.splcc.data.User;

public class Email {

	
	//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
	public void sendRoundNotification(Review review, User user) throws EmailException{
		
		String assunto = null;
		String mensagem = null;
		String emailDestino = user.getEmail();
		
		if(review.getResult().equals("Aceito")){
			assunto = "Resultado: Papper Aceito";
			mensagem = "Parabens Seu papper foi Aceito no evento";
		}
		
		if(review.getResult().equals("Rejeitado")){
			assunto = "Resultado Papper";
			mensagem = "Infelizmente Seu papper nao foi Aceito no evento.";
		}
		
		//#if ${ReviewRoundofReview} == "T" and ${NotificationsAceptanceRejection} == "T" 
		if(review.getResult().equals("Em Analise")){
			assunto = "Resultado Round Review Papper!";
			mensagem = "O round de revisão atual de seu papper é" + review.getRound() + "como resultado deste round seu papper esta em analise seguem observacoes a serem corrigidas: " + review.getDescription() + "  Use este numero de identificacao para atualizar correcoes solicitadas na revisao" + review.getIdReview();
		}
		//#endif
		
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
		
	}
	//#endif
	
	
}
