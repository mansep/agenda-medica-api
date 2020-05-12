package com.mansep.agenda.service.impl;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalAppointmentAvailabilityDto;
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
		return mAppointmentRepository.findByScheduleAndMedicalOffice(schedule, mOffice);
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
		mAppointment.setCreatedAt(editMedicalAppointment.getCreatedAt());
		mAppointment.setUpdatedAt(new Date());
		return mAppointmentRepository.save(new MedicalAppointment(mAppointment));
	}

	@Override
	public MedicalAppointment create(MedicalAppointmentDto mAppointment) throws BadRequestException {
		MedicalAppointment newMedicalAppointment = new MedicalAppointment(mAppointment);

		// Revisar duplicidad
		if (this.findOne(mAppointment.getSchedule(), new MedicalOffice(mAppointment.getMedicalOffice())) != null) {
			throw new BadRequestException("Oficina ingresada está en uso");
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

	@Override
	public List<MedicalAppointmentAvailabilityDto> findAvailability(
			List<MedicalAppointmentAvailabilityDto> mAppointmentsAppointmentAvailabilityDtos)
			throws BadRequestException {
		for (MedicalAppointmentAvailabilityDto mAppointment : mAppointmentsAppointmentAvailabilityDtos) {
			MedicalAppointment hora = this.mAppointmentRepository.findByScheduleAndMedicalOffice(
					mAppointment.getSchedule(), new MedicalOffice(mAppointment.getMedicalOffice()));
			if (hora == null) {
				mAppointment.setAvailability(true);
			} else {
				mAppointment.setAvailability(false);
			}
		}
		return mAppointmentsAppointmentAvailabilityDtos;
	}

	@Override
	public List<MedicalAppointment> createBulk(List<MedicalAppointmentDto> mAppointmentDtos)
			throws BadRequestException, Exception {
		List<MedicalAppointment> created = new ArrayList<MedicalAppointment>();
		MedicalAppointmentDto current = null;
		try {
			for (MedicalAppointmentDto mAppointment : mAppointmentDtos) {
				current = mAppointment;
				created.add(this.create(mAppointment));
			}
		} catch (BadRequestException ex) {
			for (MedicalAppointment create : created) {
				this.mAppointmentRepository.delete(create);
			}
			if (current != null) {
				DateFormat dateFormat = new SimpleDateFormat("hh:mm");
				String strDate = dateFormat.format(current.getSchedule());
				throw new BadRequestException("La oficina " + current.getMedicalOffice().getName()
						+ " está en uso a las " + strDate + "hrs., vuelva a buscar disponibilidad");
			} else {
				throw new BadRequestException("Existen horas médicas en uso, vuelva a buscar disponibilidad");
			}
		}
		return created;
	}
}