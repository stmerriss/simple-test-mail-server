package com.simple.test.mail.server;

import com.liferay.simple.test.mail.server.smtp.SmtpServer;
import java.util.Map;
import java.util.HashMap;
import com.dumbster.smtp.SmtpMessage;
import java.util.Iterator;
import java.util.ArrayList;

public class STMSMessageHandler {

	private static int totalMessagesCount = 0;
	private static Map<String, SmtpMessage> messagesMap;

	public STMSMessageHandler(SmtpServer server) {
		messagesMap = new HashMap<>();
		System.out.println("Message handler initialized.");
	}

	public static void add(String messageKey, SmtpMessage messageValue) {
		if ((messageValue != null) && (messageValue instanceof SmtpMessage)) {
			messagesMap.put(messageKey, messageValue);
		} else {
			System.out.println("Not an SmtpMessage!");
		}
	}

	public static void remove(String messageKey) {
		messagesMap.remove(messageKey);
	}

	public static void getNewMessages(SmtpServer server) {
		if(server.getNumMessages() > 0) {
			Iterator itr = server.getMessagesAsItr();
			SmtpMessage messageValue;
			String messageKey;

			if(itr.hasNext()){
				messageValue = (SmtpMessage)itr.next();
				messageKey = messageValue.getHeaderValue("Message-ID");

				// System.out.println(messageValue.toString());
				// System.out.println(messageKey);
				add(messageKey, messageValue);
				itr.remove();
			}
		}
	}

	public static String getBody(String messageKey) {
		return messagesMap.get(messageKey).getBody();
	}

	public static ArrayList<String> getHeaderNames(String messageKey) {
		Iterator itr = messagesMap.get(messageKey).getHeaderNames();
		ArrayList<String> result = new ArrayList<String>();
		if(itr.hasNext()) {
			result.add((String)itr.next());
		}

		return result;
	}

	public static String getFirstHeaderValue(String messageKey, String headerName) {
		return messagesMap.get(messageKey).getHeaderValue(headerName);
	}

	public static String[] getHeaderValues(String messageKey, String headerName) {
		return messagesMap.get(messageKey).getHeaderValues(headerName);
	}

	public static String toString(String messageKey) {
		return messagesMap.get(messageKey).toString();
	}

	public static Iterator getMessageKeys() {
		return messagesMap.keySet().iterator();
	}

	public static int getNumMessages() {
		return messagesMap.size();
	}

	public static boolean isEmpty() {
		return messagesMap.isEmpty();
	}

	public static boolean exists(String messageKey) {
		return messagesMap.containsKey(messageKey);
	}
}