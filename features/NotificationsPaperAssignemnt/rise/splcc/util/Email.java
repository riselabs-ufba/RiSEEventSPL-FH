package rise.splcc.util;

public class Email {

	public void sendNotification(User user, Review review) throws EmailException{

		original(user, review);
		
		//#if ${NotificationsPaperAssignemnt} == "T"
		String assunto3 = "Pappers para revisao";
		String mensagem3 = "Seguem em anexos pappers para revisao!";
		String emailDestino3 = user.getEmail();
		//#endif
		
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
}
