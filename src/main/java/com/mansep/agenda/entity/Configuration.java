package com.mansep.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.mansep.agenda.dto.ConfigurationDto;

import java.io.Serializable;

@Entity
public class Configuration implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 2000, nullable = false)
    private String url;
    @Column(length = 200, nullable = false)
    private String emailFrom;
    @Column(length = 200, nullable = false)
    private String emailFromName;
    @Column(length = 2000, nullable = false)
    private String smtpHost;
    @Column(nullable = false)
    private int smtpPort;
    @Column(length = 200, nullable = false)
    private String smtpUserName;
    @Column(length = 200, nullable = false)
    private String smtpPassword;

    public Configuration() {
    }

    public Configuration(ConfigurationDto config) {
        if (config == null)
            return;
        setId(config.getId());
        setName(config.getName());
        setUrl(config.getUrl());
        setEmailFrom(config.getEmailFrom());
        setEmailFromName(config.getEmailFromName());
        setSmtpHost(config.getSmtpHost());
        setSmtpPort(config.getSmtpPort());
        setSmtpUserName(config.getSmtpUserName());
        setSmtpPassword(config.getSmtpPassword());
    }

    public ConfigurationDto toDto() {
        ConfigurationDto config = new ConfigurationDto();
        config.setId(this.getId());
        config.setName(this.getName());
        config.setUrl(this.getUrl());
        config.setEmailFrom(this.getEmailFrom());
        config.setEmailFromName(this.getEmailFromName());
        config.setSmtpHost(this.getSmtpHost());
        config.setSmtpPort(this.getSmtpPort());
        config.setSmtpUserName(this.getSmtpUserName());
        config.setSmtpPassword(this.getSmtpPassword());
        return config;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}