package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalAppointmentReservedDto;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalAppointmentReserved;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.MedicalAppointmentReservedRepository;
import com.mansep.agenda.service.MedicalAppointmentReservedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "medicalAppointmentReservedService")
public class MedicalAppointmentReservedServiceImpl implements MedicalAppointmentReservedService {

	@Autowired
	private MedicalAppointmentReservedRepository mAppointmentReservedRepository;

	public List<MedicalAppointmentReserved> findAll() {
		List<MedicalAppointmentReserved> list = new ArrayList<>();
		mAppointmentReservedRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public MedicalAppointmentReserved findOne(MedicalAppointment mMedicalAppointment) {
		return mAppointmentReservedRepository.findByMedicalAppointment(mMedicalAppointment);
	}

	@Override
	public MedicalAppointmentReserved findById(Long id) {
		return mAppointmentReservedRepository.findById(id).get();
	}

	@Override
	public MedicalAppointmentReserved update(Long id, MedicalAppointmentReservedDto mAppointmentReserved) throws NotFoundException {
		MedicalAppointmentReserved editMedicalAppointmentReserved = this.findById(id);
		if (editMedicalAppointmentReserved == null) {
			throw new NotFoundException("Reserva no encontrada");
		}
		mAppointmentReserved.setId(editMedicalAppointmentReserved.getId());
		mAppointmentReserved.setCreatedAt(editMedicalAppointmentReserved.getCreatedAt());
		mAppointmentReserved.setUpdatedAt(new Date());
		return mAppointmentReservedRepository.save(new MedicalAppointmentReserved(mAppointmentReserved));
	}

	@Override
	public MedicalAppointmentReserved create(MedicalAppointmentReservedDto mAppointmentReserved) throws BadRequestException {
		MedicalAppointmentReserved newMedicalAppointmentReserved = new MedicalAppointmentReserved(mAppointmentReserved);

		// Revisar duplicidad
		MedicalAppointmentReserved ma = this.findOne(mAppointmentReserved.getMedicalAppointment());
		if (ma == null) {
			throw new BadRequestException("Hora médica no existe");
		}
		if (ma.getUser() != null) {
			throw new BadRequestException("Hora médica no está disponible");
		}

		return mAppointmentReservedRepository.save(newMedicalAppointmentReserved);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		MedicalAppointmentReserved editMedicalAppointmentReserved = this.findById(id);
		if (editMedicalAppointmentReserved == null) {
			throw new NotFoundException("Reserva no encontrada");
		}
		editMedicalAppointmentReserved.setStatus(Status.DELETED);
		;
		mAppointmentReservedRepository.save(editMedicalAppointmentReserved);
	}
}