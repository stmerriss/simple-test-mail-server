/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.stms;

import com.stms.server.MailServer;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

	    MailServer mailServer = new MailServer();

	    mailServer.start(MailServer.SMTP_PORT);
    }
}
