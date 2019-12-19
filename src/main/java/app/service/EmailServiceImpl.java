package app.service;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaEmailSender;

	Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Override
	public void sendMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);

		javaEmailSender.send(message);
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
			throws MessagingException, IOException {

		MimeMessage message = javaEmailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);

		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));

		helper.addAttachment("Attachment", file);

		javaEmailSender.send(message);
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String[] pathToAttachments)
			throws MessagingException, IOException {

		MimeMessage message = javaEmailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);

		int itr = 0;

		for (String pathToAttachment : pathToAttachments) {
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));

			helper.addAttachment("Attachment_" + itr, file);

			itr++;
		}

		javaEmailSender.send(message);
	}

}
