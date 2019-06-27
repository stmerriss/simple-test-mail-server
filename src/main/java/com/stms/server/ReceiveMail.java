package com.stms.server;

import com.sun.mail.pop3.POP3Store;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import java.util.Properties;

/**
 * @author Shane Merriss
 */
public class ReceiveMail {

	public ReceiveMail(String pop3Host, String storeType, String user, String password) {


		Properties properties = new Properties();

		properties.put("mail.pop3.host", pop3Host);
		Session emailSession = Session.getDefaultInstance(properties);


		try(
			POP3Store emailStore =  (POP3Store) emailSession.getStore(storeType);

			Folder emailFolder = emailStore.getFolder("INBOX");
		) {

			emailStore.connect(user, password);
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();

			for (Message message : messages) {
				// Do Something;
			}


			emailFolder.close();
			emailStore.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


	public static final Integer SMTP_PORT = 25;
}
