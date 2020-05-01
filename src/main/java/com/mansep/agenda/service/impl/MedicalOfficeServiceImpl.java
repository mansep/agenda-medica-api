package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalOfficeDto;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.MedicalOfficeRepository;
import com.mansep.agenda.service.MedicalOfficeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "medicalOfficeService")
public class MedicalOfficeServiceImpl implements MedicalOfficeService {

	@Autowired
	private MedicalOfficeRepository mOfficeRepository;

	public List<MedicalOffice> findAll() {
		List<MedicalOffice> list = new ArrayList<>();
		mOfficeRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public MedicalOffice findOne(String code) {
		return mOfficeRepository.findByCode(code);
	}

	@Override
	public MedicalOffice findById(Long id) {
		return mOfficeRepository.findById(id).get();
	}

	@Override
	public MedicalOffice update(Long id, MedicalOfficeDto mOffice) throws NotFoundException {
		MedicalOffice editMedicalOffice = this.findById(id);
		if (editMedicalOffice == null) {
			throw new NotFoundException("Oficina no encontrada");
		}
		mOffice.setId(editMedicalOffice.getId());
		mOffice.setCreatedAt(editMedicalOffice.getCreatedAt());
		mOffice.setUpdatedAt(new Date());
		return mOfficeRepository.save(new MedicalOffice(mOffice));
	}

	@Override
	public MedicalOffice create(MedicalOfficeDto mOffice) throws BadRequestException {
		MedicalOffice newMedicalOffice = new MedicalOffice(mOffice);
		newMedicalOffice.setCode(newMedicalOffice.getCode().trim().toUpperCase());

		// Revisar duplicidad
		if (mOfficeRepository.findByCode(newMedicalOffice.getCode()) != null) {
			throw new BadRequestException("Código ingresado está en uso");
		}

		return mOfficeRepository.save(newMedicalOffice);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		MedicalOffice editMedicalOffice = this.findById(id);
		if (editMedicalOffice == null) {
			throw new NotFoundException("Oficina no encontrada");
		}
		editMedicalOffice.setStatus(Status.DELETED);
		;
		mOfficeRepository.save(editMedicalOffice);
	}
}