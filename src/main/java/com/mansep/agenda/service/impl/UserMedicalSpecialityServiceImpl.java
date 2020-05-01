package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.UserMedicalSpecialityDto;
import com.mansep.agenda.entity.UserMedicalSpeciality;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.UserMedicalSpecialityRepository;
import com.mansep.agenda.service.UserMedicalSpecialityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userMedicalSpecialityService")
public class UserMedicalSpecialityServiceImpl implements UserMedicalSpecialityService {

	@Autowired
	private UserMedicalSpecialityRepository userMedicalSpecialityRepository;

	public List<UserMedicalSpeciality> findAll() {
		List<UserMedicalSpeciality> list = new ArrayList<>();
		userMedicalSpecialityRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public UserMedicalSpeciality findById(Long id) {
		return userMedicalSpecialityRepository.findById(id).get();
	}

	@Override
	public UserMedicalSpeciality update(Long id, UserMedicalSpecialityDto userMedicalSpeciality) throws NotFoundException {
		UserMedicalSpeciality editUserMedicalSpeciality = this.findById(id);
		if (editUserMedicalSpeciality == null) {
			throw new NotFoundException("Especialidad de doctor  no encontrada");
		}
		userMedicalSpeciality.setId(editUserMedicalSpeciality.getId());
		userMedicalSpeciality.setCreatedAt(editUserMedicalSpeciality.getCreatedAt());
		userMedicalSpeciality.setUpdatedAt(new Date());
		return userMedicalSpecialityRepository.save(new UserMedicalSpeciality(userMedicalSpeciality));
	}

	@Override
	public UserMedicalSpeciality create(UserMedicalSpecialityDto userMedicalSpeciality) throws BadRequestException {
		UserMedicalSpeciality newUserMedicalSpeciality = new UserMedicalSpeciality(userMedicalSpeciality);
		return userMedicalSpecialityRepository.save(newUserMedicalSpeciality);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		UserMedicalSpeciality editUserMedicalSpeciality = this.findById(id);
		if (editUserMedicalSpeciality == null) {
			throw new NotFoundException("Especialidad de doctor  no encontrada");
		}
		editUserMedicalSpeciality.setStatus(Status.DELETED);
		;
		userMedicalSpecialityRepository.save(editUserMedicalSpeciality);
	}
}