package com.mansep.agenda.controller;

import com.mansep.agenda.dto.ConfigurationDto;
import com.mansep.agenda.entity.Configuration;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.service.ConfigurationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/configuration")
public class ConfigurationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

    @Autowired
    ConfigurationService configurationService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<ConfigurationDto> getAllConfiguration() {
        try {
            Configuration configuration = configurationService.find();
            if (configuration != null) {
                configuration.setSmtpPassword(null);
                return ResponseEntity.ok(configuration.toDto());
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error al cargar configuración", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<ConfigurationDto> updateConfiguration(@RequestBody ConfigurationDto editConfiguration) {
        try {
            Configuration configuration = configurationService.save(editConfiguration);
            configuration.setSmtpPassword(null);
            return ResponseEntity.ok(configuration.toDto());
        } catch (BadRequestException e) {
            LOGGER.error("Error al guardar configuración", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error al guardar configuración", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}