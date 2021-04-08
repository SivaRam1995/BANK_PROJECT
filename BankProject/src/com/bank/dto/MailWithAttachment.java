package com.bank.dto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailWithAttachment {
	public void sendEmail(String email, String out,String home,String sub,String bodytxt) throws FileNotFoundException {
		ResourceBundle bundle = ResourceBundle.getBundle("com.bank.utilities.mysql-info");
		String email_id = bundle.getString("email_id");
		String email_password = bundle.getString("email_password");
		String to = email; 
		String from = email_id; 
		String password = email_password; 
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
			message.setFrom(new InternetAddress(from, "ABC BANK"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(sub);
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText(bodytxt);

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = ""+home+"\\Downloads\\" + out + ".pdf";
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(new File(filename).getName());

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
