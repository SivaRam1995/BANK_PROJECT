package com.bank.dao;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TransactionMail {

	
	
	public void sendTransactionEmail(String email, String otp) {
		ResourceBundle bundle = ResourceBundle.getBundle("com.bank.utilities.mysql-info");
		String email_id = bundle.getString("email_id");
		String email_password = bundle.getString("email_password");
		
		
		String to = email; // to address. It can be any like gmail, yahoo etc.
		  String from = email_id; // from address. As this is using Gmail SMTP your from address should be gmail
		  String password = email_password; // password for from gmail address that you have used in above line. 

		  Properties prop = new Properties();
		  prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "465");
		  prop.put("mail.smtp.auth", "true");
		  prop.put("mail.smtp.socketFactory.port", "465");
		  prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		  Session sessionn = Session.getInstance(prop, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(from, password);
		   }
		  });

		  try {

		   Message message = new MimeMessage(sessionn);
		   message.setFrom(new InternetAddress(from,"ABC BANK"));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		   message.setSubject("OTP");
		   message.setText("Your ABC Bank OTP is..."+otp);

		   Transport.send(message);

		  } catch (MessagingException | UnsupportedEncodingException e) {
		   e.printStackTrace();
		  }
		
	}

}
