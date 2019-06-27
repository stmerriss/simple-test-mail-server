package com.stms.server;

import com.sun.mail.smtp.SMTPMessage;

import javax.mail.Message;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author Shane Merriss
 */
public class MailConnectionHandler extends Thread{

	MailConnectionHandler(Socket socket) {
		_socket = socket;
	}

	public void run() {
		try (
			DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(_socket.getInputStream()));
			PrintWriter out = new PrintWriter(_socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			Scanner scanner = new Scanner(new InputStreamReader(_socket.getInputStream()));
			Scanner input = new Scanner(new InputStreamReader(_socket.getInputStream(), StandardCharsets.ISO_8859_1)).useDelimiter(CRLF);
			_socket) {

			while(scanner.hasNext()) {
				System.out.println(scanner.next());

				Message message = new SMTPMessage();
			}

			System.out.println(dataInputStream.readUTF());
			System.out.println(in.readLine());
			System.out.println();
		}
		catch (IOException ioe) {
			_log.log(Level.WARNING, "Unable to read message!", ioe);
		}
	}

	private static final Pattern CRLF = Pattern.compile("\r\n");

	private Logger _log = Logger.getLogger(MailConnectionHandler.class.getName());

	private final Socket _socket;
}
