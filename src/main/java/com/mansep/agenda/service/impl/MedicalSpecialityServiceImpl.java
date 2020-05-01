package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalSpecialityDto;
import com.mansep.agenda.entity.MedicalSpeciality;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.MedicalSpecialityRepository;
import com.mansep.agenda.service.MedicalSpecialityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "medicalSpecialityService")
public class MedicalSpecialityServiceImpl implements MedicalSpecialityService {

	@Autowired
	private MedicalSpecialityRepository mSpecialityRepository;

	public List<MedicalSpeciality> findAll() {
		List<MedicalSpeciality> list = new ArrayList<>();
		mSpecialityRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public MedicalSpeciality findOne(String code) {
		return mSpecialityRepository.findByCode(code);
	}

	@Override
	public MedicalSpeciality findById(Long id) {
		return mSpecialityRepository.findById(id).get();
	}

	@Override
	public MedicalSpeciality update(Long id, MedicalSpecialityDto mSpeciality) throws NotFoundException {
		MedicalSpeciality editMedicalSpeciality = this.findById(id);
		if (editMedicalSpeciality == null) {
			throw new NotFoundException("Especialidad medica no encontrada");
		}
		mSpeciality.setId(editMedicalSpeciality.getId());
		mSpeciality.setCreatedAt(editMedicalSpeciality.getCreatedAt());
		mSpeciality.setUpdatedAt(new Date());
		return mSpecialityRepository.save(new MedicalSpeciality(mSpeciality));
	}

	@Override
	public MedicalSpeciality create(MedicalSpecialityDto mSpeciality) throws BadRequestException {
		MedicalSpeciality newMedicalSpeciality = new MedicalSpeciality(mSpeciality);
		newMedicalSpeciality.setCode(newMedicalSpeciality.getCode().trim().toUpperCase());

		// Revisar duplicidad
		if (mSpecialityRepository.findByCode(newMedicalSpeciality.getCode()) != null) {
			throw new BadRequestException("Código ingresado está en uso");
		}

		return mSpecialityRepository.save(newMedicalSpeciality);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		MedicalSpeciality editMedicalSpeciality = this.findById(id);
		if (editMedicalSpeciality == null) {
			throw new NotFoundException("Especialidad medica no encontrada");
		}
		editMedicalSpeciality.setStatus(Status.DELETED);
		;
		mSpecialityRepository.save(editMedicalSpeciality);
	}
}