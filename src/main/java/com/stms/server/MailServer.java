package com.stms.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Shane Merriss
 */
public class MailServer implements Server{

	public MailServer() {
	}

	@Override
	public void start(int port) {

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			while (!_stopServer) {
				new MailConnectionHandler(serverSocket.accept()).start();
			}
		}
		catch (IOException ioe) {
			_log.log(Level.SEVERE, "Unable to create server!", ioe);
		}
	}

	public void stop() {
		_stopServer = true;
	}

	private Logger _log = Logger.getLogger(MailServer.class.getName());


	private ServerSocket _serverSocket;

	private boolean _stopServer = false;

	public static int SMTP_PORT = 25;
}
