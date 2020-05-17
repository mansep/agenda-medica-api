package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.ConfigurationDto;
import com.mansep.agenda.dto.MedicalCenterDto;
import com.mansep.agenda.dto.MedicalSpecialityDto;
import com.mansep.agenda.entity.Configuration;
import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.entity.MedicalSpeciality;
import com.mansep.agenda.service.ConfigurationService;
import com.mansep.agenda.service.MedicalCenterService;
import com.mansep.agenda.service.MedicalSpecialityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/web")
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    MedicalSpecialityService medicalSpecialityService;

    @Autowired
    MedicalCenterService medicalCenterService;

    @GetMapping("/config")
    public ResponseEntity<ConfigurationDto> getAllConfiguration() {
        try {
            Configuration configuration = configurationService.find();
            if (configuration != null) {
                ConfigurationDto conf = new ConfigurationDto();
                conf.setName(configuration.getName());
                return ResponseEntity.ok(conf);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error al cargar configuración", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/speciality")
    public ResponseEntity<List<MedicalSpecialityDto>> getAllSpeciality() {
        try {
            List<MedicalSpeciality> mSpecialities = this.medicalSpecialityService.findAll();
            return ResponseEntity.ok(MedicalSpecialityDto.toListDto(mSpecialities));
        } catch (Exception e) {
            LOGGER.error("Error al cargar configuración", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/center")
    public ResponseEntity<List<MedicalCenterDto>> getAllCenter() {
        try {
            List<MedicalCenter> mCenters = this.medicalCenterService.findAll();
            return ResponseEntity.ok(MedicalCenterDto.toListDto(mCenters));
        } catch (Exception e) {
            LOGGER.error("Error al cargar configuración", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}