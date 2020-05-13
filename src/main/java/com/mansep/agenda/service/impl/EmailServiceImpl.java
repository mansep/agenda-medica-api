package com.mansep.agenda.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mansep.agenda.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    static final String FROM = "sender@example.com";
    static final String FROMNAME = "Agenda médica";
    static final String SMTP_USERNAME = "smtp_username";
    static final String SMTP_PASSWORD = "smtp_password";
    static final String CONFIGSET = "ConfigSet";
    static final String HOST = "email-smtp.us-west-2.amazonaws.com";
    static final int PORT = 587;

    @Override
    public boolean sendEmail(String to, String subject, String body)
            throws UnsupportedEncodingException, MessagingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body, "text/html");

        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

        Transport transport = session.getTransport();

        try {
            LOGGER.debug("Enviando email");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            transport.sendMessage(msg, msg.getAllRecipients());
            LOGGER.debug("Email enviado con éxito");
            return true;
        } catch (Exception e) {
            LOGGER.error("Error al enviar email", e);
        } finally {
            LOGGER.debug("Cerrando conexión SMTP");
            transport.close();
        }
        return false;
    }
}