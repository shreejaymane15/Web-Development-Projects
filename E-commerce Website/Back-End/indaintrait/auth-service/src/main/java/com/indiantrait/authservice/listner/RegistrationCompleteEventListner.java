package com.indiantrait.authservice.listner;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.indiantrait.authservice.event.RegistrationCompleteEvent;
import com.indiantrait.authservice.service.AuthService;
import com.indiantrait.authservice.entity.User;
import com.indiantrait.authservice.utility.EmailSender;



@Component
public class RegistrationCompleteEventListner implements 
						ApplicationListener<RegistrationCompleteEvent>{
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		//create the verification token for user with link
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		authService.saveVerificationTokenForUser(token, user);
		//Send email to user
		String url = event.getApplicationUrl() 
							+ "/indiantrait/customer/verifyRegistration?token="
							+ token;
		String subject = "IndianTrait - Verify Your Email";
		String message = "Dear IndianTrait community member,\n"
				+ "To verify your email address click link below:\n"
				+ url
				+ "\n"
				+ "Thanks for helping us build a better community."
				+ "Your's Truly,\n"
				+ "The IndianTrait Team";
		
		emailSender.sendEmail(user.getEmail(), message, subject);
	
	}
}
