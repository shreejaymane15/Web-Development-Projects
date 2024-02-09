package com.indiantrait.authservice.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail,
							String body,
							String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("indiantrait@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		System.out.println("Mail Send....");
	}
}
