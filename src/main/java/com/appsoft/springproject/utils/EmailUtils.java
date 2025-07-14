package com.appsoft.springproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String subject, String message) {
	
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(toEmail);
		email.setSubject(subject);
		email.setText(message);
		
		mailSender.send(email);
	}

}
