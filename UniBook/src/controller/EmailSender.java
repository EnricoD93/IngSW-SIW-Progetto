package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

	public static void sendEmail(String host, String port, String email, String password, String dest, String subject,
			String text) throws AddressException, MessagingException {

		final Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		final Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				System.out.println("email:" + email + " passw:" + password);
				return new PasswordAuthentication(email, password);
			}
		};
		final Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		final Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(email));
		final InternetAddress[] toAddresses = { new InternetAddress(dest) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(text, "text/html; charset=utf-8");
		System.out.println("invio l'email");
		// sends the e-mail
		Transport.send(msg);

	}
}