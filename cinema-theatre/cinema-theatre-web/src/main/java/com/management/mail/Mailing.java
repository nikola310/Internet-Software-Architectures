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
public class Mailing implements MailingInterface{
 
    @Autowired
    private JavaMailSender mailSender;
	
	public void sendRegistration(String mail, String token) {        
        String link = "localhost:8080/cinema-theatre/confirmatin.html?token=";
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = link + token;
        String message = "Confirm your registration on this link: ";
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(mail);
        email.setSubject(subject);
        email.setText(message + confirmationUrl);
        mailSender.send(email);
	}

	public void sendInvitation(String mail, String friendsName, String eventName, int friendToken, int eventToken) {
		
	}

	public void sendFriendRequest(String mail, String friendsName, int token) {
		
	}
}
