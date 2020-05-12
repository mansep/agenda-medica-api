package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.MedicalAppointmentAvailabilityDto;
import com.mansep.agenda.dto.MedicalAppointmentDto;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.MedicalAppointmentService;

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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/medical-appointment")
public class MedicalAppointmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalAppointmentController.class);

    @Autowired
    MedicalAppointmentService mAppointmentService;

    @GetMapping("/")
    public ResponseEntity<List<MedicalAppointmentDto>> getAllMedicalAppointment() {
        try {
            List<MedicalAppointment> mAppointments = mAppointmentService.findAll();
            return ResponseEntity.ok(MedicalAppointmentDto.toListDto(mAppointments));
        } catch (Exception e) {
            LOGGER.error("Error al cargar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalAppointmentDto> getMedicalAppointment(@PathVariable Long id) {
        try {
            MedicalAppointment mAppointment = mAppointmentService.findById(id);
            return ResponseEntity.ok(mAppointment.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalAppointmentDto> updateMedicalAppointment(@PathVariable Long id,
            @RequestBody MedicalAppointmentDto mAppointment) {
        try {
            MedicalAppointment newMedicalAppointment = mAppointmentService.update(id, mAppointment);
            return ResponseEntity.ok(newMedicalAppointment.toDto());
        } catch (NotFoundException e) {
            LOGGER.error("Error al editar hora médica", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error al editar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicalAppointment(@PathVariable Long id) {
        try {
            mAppointmentService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @PostMapping("/availability/")
    public ResponseEntity<List<MedicalAppointmentAvailabilityDto>> getAvailability(
            @RequestBody List<MedicalAppointmentAvailabilityDto> mAppointment) {
        try {
            List<MedicalAppointmentAvailabilityDto> newMedicalAppointment = mAppointmentService
                    .findAvailability(mAppointment);
            return ResponseEntity.ok(newMedicalAppointment);
        } catch (BadRequestException e) {
            LOGGER.error("Error al crear hora médica", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error al crear hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @PostMapping("/")
    public ResponseEntity<MedicalAppointmentDto> createMedicalAppointment(
            @RequestBody MedicalAppointmentDto mAppointment) {
        try {
            MedicalAppointment newMedicalAppointment = mAppointmentService.create(mAppointment);
            return ResponseEntity.ok(newMedicalAppointment.toDto());
        } catch (BadRequestException e) {
            LOGGER.error("Error al crear hora médica", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error al crear hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @PostMapping("/bulk/")
    public ResponseEntity<List<MedicalAppointmentDto>> createBulkMedicalAppointment(
            @RequestBody List<MedicalAppointmentDto> mAppointment) {
        try {
            List<MedicalAppointment> newMedicalAppointment = mAppointmentService.createBulk(mAppointment);
            return ResponseEntity.ok(MedicalAppointmentDto.toListDto(newMedicalAppointment));
        } catch (BadRequestException e) {
            LOGGER.error("Error al crear hora médica", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error al crear hora médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}