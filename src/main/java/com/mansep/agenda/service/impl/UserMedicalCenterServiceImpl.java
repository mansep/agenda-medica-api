package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.mansep.agenda.dto.UserMedicalCenterDto;
import com.mansep.agenda.entity.UserMedicalCenter;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.UserMedicalCenterRepository;
import com.mansep.agenda.service.UserMedicalCenterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userMedicalCenterService")
public class UserMedicalCenterServiceImpl implements UserMedicalCenterService {

	@Autowired
	private UserMedicalCenterRepository userMedicalCenterRepository;

	public List<UserMedicalCenter> findAll() {
		List<UserMedicalCenter> list = new ArrayList<>();
		userMedicalCenterRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public UserMedicalCenter findById(Long id) {
		return userMedicalCenterRepository.findById(id).get();
	}

	@Override
	public UserMedicalCenter update(Long id, UserMedicalCenterDto userMedicalCenter) throws NotFoundException {
		UserMedicalCenter editUserMedicalCenter = this.findById(id);
		if (editUserMedicalCenter == null) {
			throw new NotFoundException("Centro médico de doctor  no encontrada");
		}
		userMedicalCenter.setId(editUserMedicalCenter.getId());
		return userMedicalCenterRepository.save(editUserMedicalCenter);
	}

	@Override
	public UserMedicalCenter create(UserMedicalCenterDto userMedicalCenter) throws BadRequestException {
		UserMedicalCenter newUserMedicalCenter = new UserMedicalCenter(userMedicalCenter);
		return userMedicalCenterRepository.save(newUserMedicalCenter);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		UserMedicalCenter editUserMedicalCenter = this.findById(id);
		if (editUserMedicalCenter == null) {
			throw new NotFoundException("Centro médico de doctor  no encontrada");
		}
		editUserMedicalCenter.setStatus(Status.DELETED);
		;
		userMedicalCenterRepository.save(editUserMedicalCenter);
	}
}