package rise.splcc.business;

public class AssignmentControl {
	
	//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"	
	public void emailNotification(User user, Review review, Email email) throws EmailException{
		email.sendNotification(user, review);
	}
	//#endif
}
