package com.management.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
		try {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
		String link = "http://localhost:8080/cinema-theatre/confirmation.html?token=";
		String subject = "Registration Confirmation";
		String confirmationUrl = link + token;
		String content = "Confirm your registration on this " + "<a href=\""+ confirmationUrl + "\">link.</a>";
		
		message.setContent(content, "text/html");
		helper.setTo(mail);
		helper.setSubject(subject);

		mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendInvitation(String mail) {
		String subject = "Event reservation";
		String message = "You reserved a seat.";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mail);
		email.setSubject(subject);
		email.setText(message);
		mailSender.send(email);
	}

	public void sendFriendRequest(String mail, String friendsName) {
		String subject = "Friend request.";
		String message = "Dear " + friendsName + ", someone want's to make friendship with you!";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mail);
		email.setSubject(subject);
		email.setText(message);
		mailSender.send(email);
	}

	@SuppressWarnings("unused")
	public void sendBidAcceptedNotification(String mail, String propsName) {
		String subject = "Your bid is accepted!";
		String link = "localhost:8080/cinema-theatre/fanzone.html?propsid=";
		String message = "Congratulations! Your bid for " + propsName
				+ " is accepted. You can see props status here: ";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mail);
		email.setSubject(subject);
		email.setText(message);
		mailSender.send(email);
	}

	@SuppressWarnings("unused")
	public void sendBidRejectedNotification(String mail, String propsName) {
		String subject = "Your bid is rejected!";
		String link = "localhost:8080/cinema-theatre/fanzone.html?propsid=";
		String message = "Unfortunately, your bid for " + propsName
				+ " is rejected. You can see props status here: ";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mail);
		email.setSubject(subject);
		email.setText(message);
		mailSender.send(email);
	}
}
