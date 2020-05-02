package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.UserMedicalCenterDto;
import com.mansep.agenda.dto.UserMedicalCentersDto;
import com.mansep.agenda.entity.UserMedicalCenter;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface UserMedicalCenterService {

    UserMedicalCenter create(UserMedicalCenterDto userMedicalCenter)
            throws BadRequestException, Exception;

    UserMedicalCenter update(Long id, UserMedicalCenterDto userMedicalCenter)
            throws NotFoundException;

    List<UserMedicalCenter> findAll();

    void delete(Long id) throws NotFoundException;

    UserMedicalCenter findById(Long id) throws NotFoundException;

    UserMedicalCentersDto saveMedicalCenters(UserMedicalCentersDto userMedicalCentersDto)
    throws NotFoundException;
}