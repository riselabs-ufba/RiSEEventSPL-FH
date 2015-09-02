package rise.splcc.facade;

public class RiSEventFacade {

	//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"
	public void emailNotification (User user, Review review, Email email) throws EmailException{
		assignments.emailNotification(user, review, email);
	}
	//#endif
}
