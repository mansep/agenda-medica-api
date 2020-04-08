package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.mansep.agenda.dto.MedicalBuildingDto;
import com.mansep.agenda.entity.MedicalBuilding;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.MedicalBuildingRepository;
import com.mansep.agenda.service.MedicalBuildingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "medicalBuildingService")
public class MedicalBuildingServiceImpl implements MedicalBuildingService {

	@Autowired
	private MedicalBuildingRepository mBuildingRepository;

	public List<MedicalBuilding> findAll() {
		List<MedicalBuilding> list = new ArrayList<>();
		mBuildingRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public MedicalBuilding findOne(String code) {
		return mBuildingRepository.findByCode(code);
	}

	@Override
	public MedicalBuilding findById(Long id) {
		return mBuildingRepository.findById(id).get();
	}

	@Override
	public MedicalBuilding update(Long id, MedicalBuildingDto mBuilding) throws NotFoundException {
		MedicalBuilding editMedicalBuilding = this.findById(id);
		if (editMedicalBuilding == null) {
			throw new NotFoundException("Edificio no encontrado");
		}
		mBuilding.setId(editMedicalBuilding.getId());
		return mBuildingRepository.save(editMedicalBuilding);
	}

	@Override
	public MedicalBuilding create(MedicalBuildingDto mBuilding) throws BadRequestException {
		MedicalBuilding newMedicalBuilding = new MedicalBuilding(mBuilding);
		newMedicalBuilding.setCode(newMedicalBuilding.getCode().trim().toUpperCase());

		// Revisar duplicidad
		if (mBuildingRepository.findByCode(newMedicalBuilding.getCode()) != null) {
			throw new BadRequestException("Código ingresado está en uso");
		}

		return mBuildingRepository.save(newMedicalBuilding);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		MedicalBuilding editMedicalBuilding = this.findById(id);
		if (editMedicalBuilding == null) {
			throw new NotFoundException("Edificio no encontrado");
		}
		editMedicalBuilding.setStatus(Status.DELETED);
		;
		mBuildingRepository.save(editMedicalBuilding);
	}
}