package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalAppointmentDto;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.MedicalAppointmentRepository;
import com.mansep.agenda.service.MedicalAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "medicalAppointmentService")
public class MedicalAppointmentServiceImpl implements MedicalAppointmentService {

	@Autowired
	private MedicalAppointmentRepository mAppointmentRepository;

	public List<MedicalAppointment> findAll() {
		List<MedicalAppointment> list = new ArrayList<>();
		mAppointmentRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public MedicalAppointment findOne(Date schedule, MedicalOffice mOffice) {
		return mAppointmentRepository.findByScheduleAndOffice(schedule, mOffice);
	}

	@Override
	public MedicalAppointment findById(Long id) {
		return mAppointmentRepository.findById(id).get();
	}

	@Override
	public MedicalAppointment update(Long id, MedicalAppointmentDto mAppointment) throws NotFoundException {
		MedicalAppointment editMedicalAppointment = this.findById(id);
		if (editMedicalAppointment == null) {
			throw new NotFoundException("Hora médica no encontrada");
		}
		mAppointment.setId(editMedicalAppointment.getId());
		return mAppointmentRepository.save(editMedicalAppointment);
	}

	@Override
	public MedicalAppointment create(MedicalAppointmentDto mAppointment) throws BadRequestException {
		MedicalAppointment newMedicalAppointment = new MedicalAppointment(mAppointment);

		// Revisar duplicidad
		if (this.findOne(mAppointment.getSchedule(), mAppointment.getMedicalOffice()) == null) {
			throw new BadRequestException("Hora ingresada está en uso");
		}

		return mAppointmentRepository.save(newMedicalAppointment);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		MedicalAppointment editMedicalAppointment = this.findById(id);
		if (editMedicalAppointment == null) {
			throw new NotFoundException("Hora médica no encontrada");
		}
		editMedicalAppointment.setStatus(Status.DELETED);
		;
		mAppointmentRepository.save(editMedicalAppointment);
	}
}