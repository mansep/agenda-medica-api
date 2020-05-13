package com.mansep.agenda.service;

import java.util.Date;
import java.util.List;

import com.mansep.agenda.dto.MedicalAppointmentAvailabilityDto;
import com.mansep.agenda.dto.MedicalAppointmentDto;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalAppointmentView;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface MedicalAppointmentService {

    MedicalAppointment create(MedicalAppointmentDto mAppointment) throws BadRequestException, Exception;

    List<MedicalAppointment> createBulk(List<MedicalAppointmentDto> mAppointment) throws BadRequestException, Exception;

    List<MedicalAppointmentAvailabilityDto> findAvailability(List<MedicalAppointmentAvailabilityDto> mAppointment)
            throws BadRequestException;

    MedicalAppointment update(Long id, MedicalAppointmentDto mAppointment) throws NotFoundException;

    List<MedicalAppointment> findAll();

    void delete(Long id) throws NotFoundException;

    MedicalAppointment findOne(Date schedule, MedicalOffice mOffice) throws BadRequestException, NotFoundException;

    MedicalAppointment findById(Long id) throws NotFoundException;

    List<MedicalAppointmentView> findByMedicalSpecialityIdAndMedicalCenterId(Long specialityID, Long centerId);
}