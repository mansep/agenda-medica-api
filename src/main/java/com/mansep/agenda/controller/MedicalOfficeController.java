package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.MedicalOfficeDto;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.MedicalOfficeService;

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
@RequestMapping(value = "/medical-office")
public class MedicalOfficeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalOfficeController.class);

    @Autowired
    MedicalOfficeService mOfficeService;

    @GetMapping("/")
    public ResponseEntity<List<MedicalOfficeDto>> getAllMedicalOffice() {
        try {
            List<MedicalOffice> mOffices = mOfficeService.findAll();
            return ResponseEntity.ok(MedicalOfficeDto.toListDto(mOffices));
        } catch (Exception e) {
            LOGGER.error("Error al cargar oficina", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalOfficeDto> getMedicalOffice(@PathVariable Long id) {
        try {
            MedicalOffice mOffice = mOfficeService.findById(id);
            return ResponseEntity.ok(mOffice.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar oficina", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalOfficeDto> updateMedicalOffice(@PathVariable Long id,
            @RequestBody MedicalOfficeDto mOffice) {
        try {
            MedicalOffice newMedicalOffice = mOfficeService.update(id, mOffice);
            return ResponseEntity.ok(newMedicalOffice.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al editar oficina", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicalOffice(@PathVariable Long id) {
        try {
            mOfficeService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar oficina", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<MedicalOfficeDto> createMedicalOffice(@RequestBody MedicalOfficeDto mOffice) {
        try {
            MedicalOffice newMedicalOffice = mOfficeService.create(mOffice);
            return ResponseEntity.ok(newMedicalOffice.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al crear oficina", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}