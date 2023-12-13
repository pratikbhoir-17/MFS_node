package com.mfs.node.dao.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import freemarker.template.Configuration;

public class EmailSenderForOtp {

	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void sendMail(String toEmail, String subject, String otp) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			message.setSubject(subject);
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			helper.setTo(toEmail);
			helper.setFrom("curbinoreply@mfsafrica.com");
//			helper.setText(otp, true);
			String contentText = getEmailContent(otp);
			helper.setText(contentText, true);
			mailSender.send(message);
		} catch (MessagingException ex) {
			// ex.printStackTrace();
		}
	}

	String getEmailContent(String otp) {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("user", otp);
		try {
			@SuppressWarnings("deprecation")
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(EmailSenderForOtp.class, "/templates/");
			cfg.getTemplate("msg.ftlh").process(model, stringWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringWriter.getBuffer().toString();
	}

}
