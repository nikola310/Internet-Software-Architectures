package com.management.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Zivko Stanisic
 *
 */
@Component
public class Mailing implements MailingInterface {

	@Autowired
	private JavaMailSender mailSender;

	public void sendRegistration(String mail, String token) {
		String link = "localhost:8080/cinema-theatre/confirmatin.html?token=";
		String subject = "Registration Confirmation";
		String confirmationUrl = link + token;
		String message = "Confirm your registration on this link: ";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mail);
		email.setSubject(subject);
		email.setText(message + confirmationUrl);
		mailSender.send(email);
	}

	public void sendInvitation(String mail, String friendsName,
			String eventName, int friendToken, int eventToken) {

	}

	public void sendFriendRequest(String mail, String friendsName, int token) {

	}

	public void sendBidAcceptedNotification(String mail, String propsName) {
		String subject = "Your bid is accepted!";
		String link = "localhost:8080/cinema-theatre/fanzone.html?propsid=";
		String message = "Congratulations! Your bid for " + propsName
				+ " is accepted. You can see props status here: ";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mail);
		email.setSubject(subject);
		email.setText(message + link);
		mailSender.send(email);
	}

	public void sendBidRejectedNotification(String mail, String propsName) {
		String subject = "Your bid is rejected!";
		String link = "localhost:8080/cinema-theatre/fanzone.html?propsid=";
		String message = "Unfortunately, your bid for " + propsName
				+ " is rejected. You can see props status here: ";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mail);
		email.setSubject(subject);
		email.setText(message + link);
		mailSender.send(email);
	}
}
