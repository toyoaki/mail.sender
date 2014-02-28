package com.mail;

import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender
{
	public void send(String propertiesPath) throws MessagingException, IOException
	{
		System.out.println("Open properties file: " + propertiesPath);

		final SendMailInfo sendMailInfo = new SendMailInfo(propertiesPath);

		System.out.println("Properties file opened");

		System.out.println("Open mail session: " + sendMailInfo.getHost() + ":" + sendMailInfo.getPort());

		Session session = Session.getDefaultInstance(sendMailInfo.getProperties(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(sendMailInfo.getUser(), sendMailInfo.getPassword());
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sendMailInfo.getFrom()));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendMailInfo.getTo()));
		message.setSubject(sendMailInfo.getSubject());
		message.setText(sendMailInfo.getTextMessage());

		System.out.println("Sending mail to: " + sendMailInfo.getTo());

		Transport.send(message);

		System.out.println("Mail sent!");
	}


	public static void main(String[] args) throws MessagingException
	{
		try
		{
			if (args == null || args.length == 0)
				System.out.println("Please pass properties file path as parameter...");
			else
				new MailSender().send(args[0]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
