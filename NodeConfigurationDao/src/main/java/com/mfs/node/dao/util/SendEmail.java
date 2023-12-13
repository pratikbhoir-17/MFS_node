package com.mfs.node.dao.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class SendEmail {

	public void sendMail(String email, String otp) {
		ApplicationContext context = new ClassPathXmlApplicationContext("email-context.xml");
		EmailSenderForOtp emailSenderForOtp = (EmailSenderForOtp) context.getBean("EmailSender");
		String subject = "One time password";
		emailSenderForOtp.sendMail(email, subject, otp);
	}
}
