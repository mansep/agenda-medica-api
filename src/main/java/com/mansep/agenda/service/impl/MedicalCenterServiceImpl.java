package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.mansep.agenda.dto.MedicalCenterDto;
import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.MedicalCenterRepository;
import com.mansep.agenda.service.MedicalCenterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "medicalCenterService")
public class MedicalCenterServiceImpl implements MedicalCenterService {

	@Autowired
	private MedicalCenterRepository mCenterRepository;

	public List<MedicalCenter> findAll() {
		List<MedicalCenter> list = new ArrayList<>();
		mCenterRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public MedicalCenter findOne(String code) {
		return mCenterRepository.findByCode(code);
	}

	@Override
	public MedicalCenter findById(Long id) {
		return mCenterRepository.findById(id).get();
	}

	@Override
	public MedicalCenter update(Long id, MedicalCenterDto mCenter) throws NotFoundException {
		MedicalCenter editMedicalCenter = this.findById(id);
		if (editMedicalCenter == null) {
			throw new NotFoundException("Centro médico no encontrado");
		}
		mCenter.setId(editMedicalCenter.getId());
		return mCenterRepository.save(editMedicalCenter);
	}

	@Override
	public MedicalCenter create(MedicalCenterDto mCenter) throws BadRequestException {
		MedicalCenter newMedicalCenter = new MedicalCenter(mCenter);
		newMedicalCenter.setCode(newMedicalCenter.getCode().trim().toUpperCase());

		// Revisar duplicidad
		if (mCenterRepository.findByCode(newMedicalCenter.getCode()) != null) {
			throw new BadRequestException("Código ingresado está en uso");
		}

		return mCenterRepository.save(newMedicalCenter);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		MedicalCenter editMedicalCenter = this.findById(id);
		if (editMedicalCenter == null) {
			throw new NotFoundException("Centro médico no encontrado");
		}
		editMedicalCenter.setStatus(Status.DELETED);
		;
		mCenterRepository.save(editMedicalCenter);
	}
}