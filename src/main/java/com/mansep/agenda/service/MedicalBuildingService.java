package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.MedicalBuildingDto;
import com.mansep.agenda.entity.MedicalBuilding;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface MedicalBuildingService {

    MedicalBuilding create(MedicalBuildingDto mBuilding) throws BadRequestException, Exception;

    MedicalBuilding update(Long id, MedicalBuildingDto mBuilding) throws NotFoundException;

    List<MedicalBuilding> findAll();

    void delete(Long id) throws NotFoundException;

    MedicalBuilding findOne(String code) throws NotFoundException;

    MedicalBuilding findById(Long id) throws NotFoundException;
}