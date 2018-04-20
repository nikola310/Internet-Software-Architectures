package com.management.mail;

/**
 * @author Zivko Stanisic
 *
 */
public interface MailingInterface {

	public void sendRegistration(String mail, String token);
	
	public void sendInvitation(String mail);
	
	public void sendFriendRequest(String mail, String friendsName);
	
	public void sendBidAcceptedNotification(String mail, String propsName);
	
	public void sendBidRejectedNotification(String mail, String propsName);
}
