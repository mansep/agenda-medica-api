package com.mansep.agenda.controller;

import java.util.List;

import com.mansep.agenda.dto.MedicalSpecialityDto;
import com.mansep.agenda.entity.MedicalSpeciality;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.service.MedicalSpecialityService;

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
@RequestMapping(value = "/medical-speciality")
public class MedicalSpecialityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalSpecialityController.class);

    @Autowired
    MedicalSpecialityService mSpecialityService;

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<MedicalSpecialityDto>> getAllMedicalSpeciality() {
        try {
            List<MedicalSpeciality> mSpecialitys = mSpecialityService.findAll();
            return ResponseEntity.ok(MedicalSpecialityDto.toListDto(mSpecialitys));
        } catch (Exception e) {
            LOGGER.error("Error al cargar especialidad médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalSpecialityDto> getMedicalSpeciality(@PathVariable Long id) {
        try {
            MedicalSpeciality mSpeciality = mSpecialityService.findById(id);
            return ResponseEntity.ok(mSpeciality.toDto());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error("Error al cargar especialidad médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalSpecialityDto> updateMedicalSpeciality(@PathVariable Long id,
            @RequestBody MedicalSpecialityDto mSpeciality) {
        try {
            MedicalSpeciality newMedicalSpeciality = mSpecialityService.update(id, mSpeciality);
            return ResponseEntity.ok(newMedicalSpeciality.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al editar especialidad médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicalSpeciality(@PathVariable Long id) {
        try {
            mSpecialityService.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar especialidad médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<MedicalSpecialityDto> createMedicalSpeciality(@RequestBody MedicalSpecialityDto mSpeciality) {
        try {
            MedicalSpeciality newMedicalSpeciality = mSpecialityService.create(mSpeciality);
            return ResponseEntity.ok(newMedicalSpeciality.toDto());
        } catch (Exception e) {
            LOGGER.error("Error al crear especialidad médica", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}