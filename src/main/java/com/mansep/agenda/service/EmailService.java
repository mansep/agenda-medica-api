package com.mansep.agenda.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.mansep.agenda.entity.Configuration;

public interface EmailService {

    boolean sendEmail(String to, String subject, String body) throws UnsupportedEncodingException, MessagingException;

    boolean sendEmail(String to, String subject, String body, Configuration config)
            throws UnsupportedEncodingException, MessagingException;

}