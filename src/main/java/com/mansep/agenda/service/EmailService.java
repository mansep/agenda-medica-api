package com.mansep.agenda.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface EmailService {

    boolean sendEmail(String to, String subject, String body) throws UnsupportedEncodingException, MessagingException;

}