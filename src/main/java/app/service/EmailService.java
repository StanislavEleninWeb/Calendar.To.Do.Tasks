package app.service;

import java.io.IOException;

import javax.mail.MessagingException;

public interface EmailService {

	void sendMessage(String to, String subject, String text);

	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
			throws MessagingException, IOException;

	void sendMessageWithAttachment(String to, String subject, String text, String[] pathToAttachments)
			throws MessagingException, IOException;
}
