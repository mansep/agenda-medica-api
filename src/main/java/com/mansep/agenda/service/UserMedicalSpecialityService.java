package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.UserMedicalSpecialityDto;
import com.mansep.agenda.entity.UserMedicalSpeciality;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface UserMedicalSpecialityService {

    UserMedicalSpeciality create(UserMedicalSpecialityDto userMedicalSpeciality)
            throws BadRequestException, Exception;

    UserMedicalSpeciality update(Long id, UserMedicalSpecialityDto userMedicalSpeciality)
            throws NotFoundException;

    List<UserMedicalSpeciality> findAll();

    void delete(Long id) throws NotFoundException;

    UserMedicalSpeciality findById(Long id) throws NotFoundException;
}