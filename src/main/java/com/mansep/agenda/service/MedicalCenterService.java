package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.MedicalCenterDto;
import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface MedicalCenterService {

    MedicalCenter create(MedicalCenterDto mCenter) throws BadRequestException, Exception;

    MedicalCenter update(Long id, MedicalCenterDto mCenter) throws NotFoundException;

    List<MedicalCenter> findAll();

    void delete(Long id) throws NotFoundException;

    MedicalCenter findOne(String code) throws NotFoundException;

    MedicalCenter findById(Long id) throws NotFoundException;
}