package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.entity.Configuration;

public class ConfigurationDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String url;
    private String emailFrom;
    private String emailFromName;
    private String smtpHost;
    private int smtpPort;
    private String smtpUserName;
    private String smtpPassword;

    @Override
    public String toString() {
        return "ConfigurationDto [name=" + name + "]";
    }

    public static List<ConfigurationDto> toListDto(List<Configuration> users) {
        List<ConfigurationDto> lu = new ArrayList<ConfigurationDto>();
        for (Configuration user : users) {
            lu.add(user.toDto());
        }
        return lu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailFromName() {
        return emailFromName;
    }

    public void setEmailFromName(String emailFromName) {
        this.emailFromName = emailFromName;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUserName() {
        return smtpUserName;
    }

    public void setSmtpUserName(String smtpUserName) {
        this.smtpUserName = smtpUserName;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }
}