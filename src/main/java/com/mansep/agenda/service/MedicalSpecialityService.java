package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.MedicalSpecialityDto;
import com.mansep.agenda.entity.MedicalSpeciality;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface MedicalSpecialityService {

    MedicalSpeciality create(MedicalSpecialityDto mSpeciality) throws BadRequestException, Exception;
    MedicalSpeciality update(Long id, MedicalSpecialityDto mSpeciality) throws NotFoundException;
    List<MedicalSpeciality> findAll();
    void delete(Long id) throws NotFoundException;
    MedicalSpeciality findOne(String code) throws NotFoundException;
    MedicalSpeciality findById(Long id) throws NotFoundException;
}