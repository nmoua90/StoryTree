package papacapstone.ServerTools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Sends an email based on an outboundAddress. Sends from a client's email
 * account.
 * 
 * Copyright (C) 2018 -- Nhia Moua, Brian LeMoine, Evan Wall
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License 3.0 as published by the
 * Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License 3.0 for
 * more details.
 * 
 * You should have received a copy of the GNU Affero General Public License 3.0
 * along with this program. If not, see
 * https://www.gnu.org/licenses/agpl-3.0.txt
 * 
 * @author Papa Capstone
 */
public class MailSender {
	private static final String clientAddress = Credentials.getEmailServerAddress();
	private static final String clientPassword = Credentials.getEmailServerPassword();
	private String outboundAddress;
	private String emailTitle;
	private String emailContent;
	private Properties props;
	private Session session;

	/**
	 * Disallow standard constructor
	 */
	private MailSender() {
	}

	/**
	 * Constructor sets the outbound address where an email will be sent to
	 * 
	 * @param outboundAddress
	 */
	public MailSender(String outboundAddress) {
		this.outboundAddress = outboundAddress;
	}

	/**
	 * Sends an email
	 * 
	 * Pre-Condition: Assumes that you have already set the outboundAddress,
	 * emailTitle, and emailContent Post-Condition: Sends an email to an
	 * outboundAddress with emailTitle and emailContent
	 */
	public boolean send() {
		prepareProperties();
		loginAuthentication();

		boolean wasSuccessfullySent = sendEmail();

		return wasSuccessfullySent;
	}

	/**
	 * Prepares a standard Registration verification email to be sent
	 */
	public void prepareRegisrationEmail(String regLink) {
		String registrationSite = "http://localhost:8080/StoryTreeApp/ActivateAccount?activateID=" + regLink;
		emailTitle = "Welcome to StoryTree! Please activate your account!";
		emailContent = "Please click this link to activate your account: " + registrationSite;
	}

	/**
	 * Sets technical details, pertaining to the client's email/server/host
	 * metadata. Required to be set prior to sending an email.
	 */
	private void prepareProperties() {
		// Get properties object
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}

	/**
	 * Verifies login credentials for client's email account.
	 */
	private void loginAuthentication() {
		// get Session
		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(clientAddress, clientPassword);
			}
		});
	}

	/**
	 * Sends an email after client's account has been verified.
	 */
	private boolean sendEmail() {
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(outboundAddress));
			message.setSubject(emailTitle);
			message.setText(emailContent);

			// send message
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

} // end of class