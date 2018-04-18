package com.liferay.simple.test.mail.server;

import com.liferay.simple.test.mail.server.smtp.SmtpServer;
import java.util.Iterator;
import org.jibble.simplewebserver.*;
import java.io.File;
import java.io.IOException;

public class SimpleTestMailServer {
	private static SmtpServer server_smtp;
	private static STMSMessageHandler messages;
	private static SimpleWebServer server_http;

	public static void main(String[] args) {
		System.out.println("Simmple Test Mail Server has started!");

		try{
		  SimpleWebServer server_http = new SimpleWebServer(new File("./"), 8181);
		}catch(IOException e){
		  e.printStackTrace();
		}

		SmtpServer server_smtp = new SmtpServer(25);
		server_smtp.start();

		STMSMessageHandler messages = new STMSMessageHandler(server_smtp);

		System.out.println("Is smtp server running: " + server_smtp.isRunning());

		while(server_smtp.isRunning()) {
			messages.getNewMessages(server_smtp);
			if(!messages.isEmpty()) {
				System.out.println("------Start-----");
				//System.out.println(messages.getMessageKeys());
				Iterator itr = messages.getMessageKeys();

				System.out.println("Has next: " + itr.hasNext());
				String messageKey;
				while(itr.hasNext()) {
					messageKey = (String)itr.next().toString();
					System.out.println("messageKey: " + messageKey);

					if(messages.exists(messageKey)) {
						System.out.println(messages.toString(messageKey));
						messages.remove(messageKey);
					}
					else {
						System.out.println("Not a valid messageKey: " + (String)itr.next());
					}
				}
				System.out.println("------End-----");
			}

			try{
				Thread.sleep(300);

			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
}