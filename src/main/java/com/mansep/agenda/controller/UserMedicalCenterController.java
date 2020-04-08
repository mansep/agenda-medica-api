package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.UserMedicalCenterDto;
import com.mansep.agenda.entity.UserMedicalCenter;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.UserMedicalCenterService;

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
@RequestMapping(value = "/user-medical-center")
public class UserMedicalCenterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMedicalCenterController.class);

    @Autowired
    UserMedicalCenterService userMedicalCenterService;

    @GetMapping("/")
    public ResponseEntity<List<UserMedicalCenterDto>> getAllUserMedicalCenter() {
        try {
            List<UserMedicalCenter> mAppointmentReserveds = userMedicalCenterService.findAll();
            return ResponseEntity.ok(UserMedicalCenterDto.toListDto(mAppointmentReserveds));
        } catch (Exception e) {
            LOGGER.error("Error al cargar centro médico de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMedicalCenterDto> getUserMedicalCenter(@PathVariable Long id) {
        try {
            UserMedicalCenter mAppointmentReserved = userMedicalCenterService.findById(id);
            return ResponseEntity.ok(mAppointmentReserved.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar centro médico de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserMedicalCenterDto> updateUserMedicalCenter(@PathVariable Long id,
            @RequestBody UserMedicalCenterDto mAppointmentReserved) {
        try {
            UserMedicalCenter newUserMedicalCenter = userMedicalCenterService.update(id, mAppointmentReserved);
            return ResponseEntity.ok(newUserMedicalCenter.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al editar centro médico de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserMedicalCenter(@PathVariable Long id) {
        try {
            userMedicalCenterService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar centro médico de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<UserMedicalCenterDto> createUserMedicalCenter(@RequestBody UserMedicalCenterDto mAppointmentReserved) {
        try {
            UserMedicalCenter newUserMedicalCenter = userMedicalCenterService.create(mAppointmentReserved);
            return ResponseEntity.ok(newUserMedicalCenter.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al crear centro médico de doctor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}