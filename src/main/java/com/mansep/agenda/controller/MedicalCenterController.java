package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.MedicalCenterDto;
import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.MedicalCenterService;

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
@RequestMapping(value = "/medical-center")
public class MedicalCenterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalCenterController.class);

    @Autowired
    MedicalCenterService mCenterService;

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<MedicalCenterDto>> getAllMedicalCenter() {
        try {
            List<MedicalCenter> mCenters = mCenterService.findAll();
            return ResponseEntity.ok(MedicalCenterDto.toListDto(mCenters));
        } catch (Exception e) {
            LOGGER.error("Error al cargar centro médico", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalCenterDto> getMedicalCenter(@PathVariable Long id) {
        try {
            MedicalCenter mCenter = mCenterService.findById(id);
            return ResponseEntity.ok(mCenter.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar centro médico", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalCenterDto> updateMedicalCenter(@PathVariable Long id,
            @RequestBody MedicalCenterDto mCenter) {
        try {
            MedicalCenter newMedicalCenter = mCenterService.update(id, mCenter);
            return ResponseEntity.ok(newMedicalCenter.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al editar centro médico", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicalCenter(@PathVariable Long id) {
        try {
            mCenterService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar centro médico", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<MedicalCenterDto> createMedicalCenter(@RequestBody MedicalCenterDto mCenter) {
        try {
            MedicalCenter newMedicalCenter = mCenterService.create(mCenter);
            return ResponseEntity.ok(newMedicalCenter.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al crear centro médico", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}