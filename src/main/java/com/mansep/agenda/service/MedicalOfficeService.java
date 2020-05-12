package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.MedicalOfficeDto;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface MedicalOfficeService {

    MedicalOffice create(MedicalOfficeDto mOffice) throws BadRequestException, Exception;

    MedicalOffice update(Long id, MedicalOfficeDto mOffice) throws NotFoundException;

    List<MedicalOffice> findAll();

    void delete(Long id) throws NotFoundException;

    List<MedicalOffice> findByIdMedicalBuilding(Long id) throws NotFoundException;

    MedicalOffice findOne(String code) throws NotFoundException;

    MedicalOffice findById(Long id) throws NotFoundException;
}