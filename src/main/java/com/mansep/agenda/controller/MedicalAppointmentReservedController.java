package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.MedicalAppointmentReservedDto;
import com.mansep.agenda.entity.MedicalAppointmentReserved;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.MedicalAppointmentReservedService;

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
@RequestMapping(value = "/medical-appointment-reserved")
public class MedicalAppointmentReservedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalAppointmentReservedController.class);

    @Autowired
    MedicalAppointmentReservedService mAppointmentReservedService;

    @GetMapping("/")
    public ResponseEntity<List<MedicalAppointmentReservedDto>> getAllMedicalAppointmentReserved() {
        try {
            List<MedicalAppointmentReserved> mAppointmentReserveds = mAppointmentReservedService.findAll();
            return ResponseEntity.ok(MedicalAppointmentReservedDto.toListDto(mAppointmentReserveds));
        } catch (Exception e) {
            LOGGER.error("Error al cargar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalAppointmentReservedDto> getMedicalAppointmentReserved(@PathVariable Long id) {
        try {
            MedicalAppointmentReserved mAppointmentReserved = mAppointmentReservedService.findById(id);
            return ResponseEntity.ok(mAppointmentReserved.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalAppointmentReservedDto> updateMedicalAppointmentReserved(@PathVariable Long id,
            @RequestBody MedicalAppointmentReservedDto mAppointmentReserved) {
        try {
            MedicalAppointmentReserved newMedicalAppointmentReserved = mAppointmentReservedService.update(id, mAppointmentReserved);
            return ResponseEntity.ok(newMedicalAppointmentReserved.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al editar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicalAppointmentReserved(@PathVariable Long id) {
        try {
            mAppointmentReservedService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @PostMapping("/")
    public ResponseEntity<MedicalAppointmentReservedDto> createMedicalAppointmentReserved(@RequestBody MedicalAppointmentReservedDto mAppointmentReserved) {
        try {
            MedicalAppointmentReserved newMedicalAppointmentReserved = mAppointmentReservedService.create(mAppointmentReserved);
            return ResponseEntity.ok(newMedicalAppointmentReserved.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al crear hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}