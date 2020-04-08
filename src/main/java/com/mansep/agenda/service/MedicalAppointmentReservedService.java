package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.MedicalAppointmentReservedDto;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalAppointmentReserved;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface MedicalAppointmentReservedService {

    MedicalAppointmentReserved create(MedicalAppointmentReservedDto mAppointmentReserved)
            throws BadRequestException, Exception;

    MedicalAppointmentReserved update(Long id, MedicalAppointmentReservedDto mAppointmentReserved)
            throws NotFoundException;

    List<MedicalAppointmentReserved> findAll();

    void delete(Long id) throws NotFoundException;

    MedicalAppointmentReserved findOne(MedicalAppointment mMedicalAppointment) throws NotFoundException;

    MedicalAppointmentReserved findById(Long id) throws NotFoundException;
}