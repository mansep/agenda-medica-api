package com.mansep.agenda.service.impl;

import java.util.Optional;

import com.mansep.agenda.dto.ConfigurationDto;
import com.mansep.agenda.entity.Configuration;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.ForbiddenException;
import com.mansep.agenda.repository.ConfigurationRepository;
import com.mansep.agenda.service.ConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public Configuration find() throws Exception, ForbiddenException {
        Optional<Configuration> config = this.configurationRepository.findById((long) 1);
        if (config.isEmpty()) {
            return null;
        }
        return config.get();
    }

    @Override
    public Configuration save(ConfigurationDto configurationDto) throws Exception, BadRequestException {
        if (configurationDto.getName() == null) {
            throw new BadRequestException("Debe ingresar nombre");
        }
        if (configurationDto.getEmailFrom() == null) {
            throw new BadRequestException("Debe ingresar email emisor");
        }
        if (configurationDto.getEmailFromName() == null) {
            throw new BadRequestException("Debe ingresar nombre de emisor");
        }
        if (configurationDto.getSmtpHost() == null) {
            throw new BadRequestException("Debe ingresar servidor SMTP");
        }
        if (configurationDto.getSmtpPort() <= 0) {
            throw new BadRequestException("Debe ingresar puerto servidor SMTP");
        }
        if (configurationDto.getSmtpUserName() == null) {
            throw new BadRequestException("Debe ingresar usuario servidor SMTP");
        }

        Configuration config = this.find();
        if (config == null) {
            if (configurationDto.getSmtpPassword() == null) {
                throw new BadRequestException("Debe ingresar contraseña servidor SMTP");
            }
        } else {
            if (configurationDto.getSmtpPassword() == null) {
                configurationDto.setSmtpPassword(config.getSmtpPassword());
            }
        }
        configurationDto.setId((long) 1);

        Configuration newConfig = new Configuration(configurationDto);
        return this.configurationRepository.save(newConfig);
    }

}