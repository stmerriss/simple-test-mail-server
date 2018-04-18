package com.liferay.simple.test.mail.server.smtp;

import com.dumbster.smtp.*;
import java.lang.*;
import java.util.Iterator;

public class SmtpServer {
	private static int SMTP_PORT;
	private static SimpleSmtpServer server;

	public SmtpServer(int port) {
		SMTP_PORT = port;
	}

	public void start() {
		server = SimpleSmtpServer.start(SMTP_PORT);
		System.out.println("Server has started on port: " + SMTP_PORT);
	}

	public void run() {
		server.run();
		System.out.print("Server is running! (party)");
	}

	public void stop() {
		server.stop();
		System.out.println("Server has been stopped.");
	}

	public Iterator getMessagesAsItr() {
		return server.getReceivedEmail();
	}

	public int getNumMessages() {
		return server.getReceivedEmailSize();
	}

	public boolean isRunning() {
		return !server.isStopped();
	}
}