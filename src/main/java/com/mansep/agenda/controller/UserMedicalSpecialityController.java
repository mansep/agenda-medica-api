package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.UserMedicalSpecialitiesDto;
import com.mansep.agenda.dto.UserMedicalSpecialityDto;
import com.mansep.agenda.entity.UserMedicalSpeciality;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.UserMedicalSpecialityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user-medical-speciality")
public class UserMedicalSpecialityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMedicalSpecialityController.class);

    @Autowired
    UserMedicalSpecialityService userMedicalSpecialityService;

    @GetMapping("/")
    public ResponseEntity<List<UserMedicalSpecialityDto>> getAllUserMedicalSpeciality() {
        try {
            List<UserMedicalSpeciality> userMedicalSpecialitys = userMedicalSpecialityService.findAll();
            return ResponseEntity.ok(UserMedicalSpecialityDto.toListDto(userMedicalSpecialitys));
        } catch (Exception e) {
            LOGGER.error("Error al cargar especialidad de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMedicalSpecialityDto> getUserMedicalSpeciality(@PathVariable Long id) {
        try {
            UserMedicalSpeciality userMedicalSpeciality = userMedicalSpecialityService.findById(id);
            return ResponseEntity.ok(userMedicalSpeciality.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar especialidad de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserMedicalSpecialityDto> updateUserMedicalSpeciality(@PathVariable Long id,
            @RequestBody UserMedicalSpecialityDto userMedicalSpeciality) {
        try {
            UserMedicalSpeciality newUserMedicalSpeciality = userMedicalSpecialityService.update(id,
                    userMedicalSpeciality);
            return ResponseEntity.ok(newUserMedicalSpeciality.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al editar especialidad de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/user/bulk")
    public ResponseEntity<UserMedicalSpecialitiesDto> updateUserMedicalSpecialities(
            @RequestBody UserMedicalSpecialitiesDto userMedicalSpecialities) {
        try {
            UserMedicalSpecialitiesDto newUserMedicalSpeciality = userMedicalSpecialityService
                    .saveMedicalSpecialities(userMedicalSpecialities);
            return ResponseEntity.ok(newUserMedicalSpeciality);
        } catch (Exception e) {
            LOGGER.error("Error al editar especialidad de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserMedicalSpeciality(@PathVariable Long id) {
        try {
            userMedicalSpecialityService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar especialidad de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<UserMedicalSpecialityDto> createUserMedicalSpeciality(
            @RequestBody UserMedicalSpecialityDto userMedicalSpeciality) {
        try {
            UserMedicalSpeciality newUserMedicalSpeciality = userMedicalSpecialityService.create(userMedicalSpeciality);
            return ResponseEntity.ok(newUserMedicalSpeciality.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al crear especialidad de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}