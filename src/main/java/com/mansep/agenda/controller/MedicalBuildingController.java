package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.MedicalBuildingDto;
import com.mansep.agenda.entity.MedicalBuilding;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.MedicalBuildingService;

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
@RequestMapping(value = "/medical-building")
public class MedicalBuildingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalBuildingController.class);

    @Autowired
    MedicalBuildingService mBuildingService;

    @GetMapping("/")
    public ResponseEntity<List<MedicalBuildingDto>> getAllMedicalBuilding() {
        try {
            List<MedicalBuilding> mBuildings = mBuildingService.findAll();
            return ResponseEntity.ok(MedicalBuildingDto.toListDto(mBuildings));
        } catch (Exception e) {
            LOGGER.error("Error al cargar edificio", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalBuildingDto> getMedicalBuilding(@PathVariable Long id) {
        try {
            MedicalBuilding mBuilding = mBuildingService.findById(id);
            return ResponseEntity.ok(mBuilding.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar edificio", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalBuildingDto> updateMedicalBuilding(@PathVariable Long id,
            @RequestBody MedicalBuildingDto mBuilding) {
        try {
            MedicalBuilding newMedicalBuilding = mBuildingService.update(id, mBuilding);
            return ResponseEntity.ok(newMedicalBuilding.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al editar edificio", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicalBuilding(@PathVariable Long id) {
        try {
            mBuildingService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar edificio", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<MedicalBuildingDto> createMedicalBuilding(@RequestBody MedicalBuildingDto mBuilding) {
        try {
            MedicalBuilding newMedicalBuilding = mBuildingService.create(mBuilding);
            return ResponseEntity.ok(newMedicalBuilding.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al crear edificio", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}