/**
 * 
 */
package com.management.mail;

/**
 * @author Zivko Stanisic
 *
 */
public interface MailingInterface {

	public void sendRegistration(String mail, String token);
	
	public void sendInvitation(String mail, String friendsName, String eventName, int friendToken, int eventToken);
	
	public void sendFriendRequest(String mail, String friendsName, int token);
}
