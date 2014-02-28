package com.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SendMailInfo
{
	private final String host;
	private final String port;
	private final String user;
	private final String password;

	private final String from;
	private final String to;
	private final String subject;
	private final String textMessage;

	private final Properties properties;


	public SendMailInfo(String propertiesPath) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(propertiesPath));
		properties = new Properties();
		properties.load(fis);

		host = properties.getProperty("mail.smtp.host");
		port = properties.getProperty("mail.port");
		user = properties.getProperty("mail.user");
		password = properties.getProperty("mail.password");

		from = properties.getProperty("send.from");
		to = properties.getProperty("send.to");
		subject = properties.getProperty("send.subject");
		textMessage = properties.getProperty("send.textMessage");
	}


	public String getHost()
	{
		return host;
	}


	public String getPort()
	{
		return port;
	}


	public String getUser()
	{
		return user;
	}


	public String getPassword()
	{
		return password;
	}


	public String getFrom()
	{
		return from;
	}


	public String getTo()
	{
		return to;
	}


	public String getSubject()
	{
		return subject;
	}


	public String getTextMessage()
	{
		return textMessage;
	}


	public Properties getProperties()
	{
		return properties;
	}
}
