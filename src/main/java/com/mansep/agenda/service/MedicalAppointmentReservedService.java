package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.MedicalAppointmentReservedDto;
import com.mansep.agenda.dto.StatusDto;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalAppointmentReserved;
import com.mansep.agenda.entity.MedicalAppointmentView;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface MedicalAppointmentReservedService {

        MedicalAppointmentReserved create(MedicalAppointmentReservedDto mAppointmentReserved)
                        throws BadRequestException, NumberFormatException, NotFoundException;

        MedicalAppointmentReserved update(Long id, MedicalAppointmentReservedDto mAppointmentReserved)
                        throws NotFoundException;

        MedicalAppointmentReserved updateStatus(Long id, StatusDto status) throws NotFoundException;

        List<MedicalAppointmentReserved> findAll();

        List<MedicalAppointmentView> findPatient() throws NotFoundException;

        MedicalAppointmentView findPatientById(Long id) throws NotFoundException;

        void delete(Long id) throws NotFoundException;

        MedicalAppointmentReserved findOne(MedicalAppointment mMedicalAppointment) throws NotFoundException;

        MedicalAppointmentReserved findById(Long id) throws NotFoundException;
}