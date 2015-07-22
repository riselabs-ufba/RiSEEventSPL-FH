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

	//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T" 
	public void sendNotification(User user, Review review) throws EmailException{

		//#if ${NotificationsDeadline} == "T"
		// esta classe eh chamada logo apos o insert do assignment
		String assunto = "Prazo de entrega de Rivisao";
		String mensagem = "O prazo de entrega é ate 15 dias antes da data oficial de Inicio do evento. Seu ID para cadastrar a revisao é:" + review.getIdReview() + " Favor usar este ID!" ;
		String emailDestino = user.getEmail();
		//#endif
	
//		//#if ${NotificationsAceptanceRejection} == "T"
//		String assunto2 = "Resultado Revisao Papper!";
//		String mensagem2 = "Seu Papper esta sendo revisado. O resultado sera encaminhado via email.";
//		String emailDestino2 = author.getEmail();
//		//#endif
		
		//#if ${NotificationsPaperAssignemnt} == "T"
		String assunto3 = "Pappers para revisao";
		String mensagem3 = "Seguem em anexos pappers para revisao!";
		String emailDestino3 = user.getEmail();
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
		
		//#if ${NotificationsPaperAssignemnt} == "T"
		// assignment email
		HtmlEmail email3 = new HtmlEmail(); 
		email3.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email3.addTo(emailDestino3, user.getNameUser()); //destinat�rio 
		email3.setFrom("riseemail@gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email3.setSubject(assunto3); // assunto do e-mail 
		
		//ESTOU ENVIADO UMA IMAGEM EM ANEXO POIS AINDA NAO CONSEGUI PEGAR DO BANCO E INSERIR AQUI
		//O ID DO SIBMISSION PARA PEGAR PDF DO BANCO ESTA NO AUTHOR PASSADO AQUI
		EmailAttachment anexo = new EmailAttachment();
	    anexo.setPath("/images/riseLabs.png");
	    anexo.setDisposition(EmailAttachment.ATTACHMENT);
	    email3.attach(anexo); 
	    
		email3.setMsg(mensagem3); //conteudo do e-mail
		
		email3.setAuthentication("riseemail@gmail.com", "password");
		email3.setSslSmtpPort( "465" ); //578 ou 465
		email3.setSSLOnConnect(true);
		email3.setStartTLSEnabled(true);
		email3.setStartTLSRequired(true);
	
		email3.send(); //envia o e-mail
		//#endif
	}
	//#endif
	
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
	
	//#if ${Bugs} == "T"
	public String sendBugtrackEmail(String nome, String assunto, String mensagem) throws EmailException{
		SimpleEmail email = new SimpleEmail();
		String msg;
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo("riseeventemail@gmail.com", "Bugtrack Event"); //destinat�rio 
		email.setFrom("riseeventemail@gmail.com", nome); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("riseeventemail@gmail.com", "senhasecreta");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		email.send(); //envia o e-mail
		msg = "Email enviado com Sucesso";
		return msg;
	}
	//#endif
}
