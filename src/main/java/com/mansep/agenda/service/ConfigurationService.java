package com.mansep.agenda.service;

import com.mansep.agenda.dto.ConfigurationDto;
import com.mansep.agenda.entity.Configuration;
import com.mansep.agenda.exception.BadRequestException;

public interface ConfigurationService {

    Configuration find() throws Exception;

    Configuration save(ConfigurationDto configurationDto) throws Exception, BadRequestException;

}