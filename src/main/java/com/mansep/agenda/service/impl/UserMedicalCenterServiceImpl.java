package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalCenterDto;
import com.mansep.agenda.dto.UserMedicalCenterDto;
import com.mansep.agenda.dto.UserMedicalCentersDto;
import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.UserMedicalCenter;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.UserMedicalCenterRepository;
import com.mansep.agenda.service.UserMedicalCenterService;
import com.mansep.agenda.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userMedicalCenterService")
public class UserMedicalCenterServiceImpl implements UserMedicalCenterService {

	@Autowired
	private UserService userService;
	
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
			throw new NotFoundException("Centro médico de doctor no encontrada");
		}
		userMedicalCenter.setId(editUserMedicalCenter.getId());
		userMedicalCenter.setCreatedAt(editUserMedicalCenter.getCreatedAt());
		userMedicalCenter.setUpdatedAt(new Date());
		return userMedicalCenterRepository.save(new UserMedicalCenter(userMedicalCenter));
	}

	@Override
	public UserMedicalCentersDto saveMedicalCenters(UserMedicalCentersDto userMedicalCentersDto)
			throws NotFoundException {
		User user = this.userService.findById(userMedicalCentersDto.getUserDoctor().getId());

		if (user == null) {
			throw new NotFoundException("Centro médico  no encontrada");
		}
		List<UserMedicalCenter> saved = new ArrayList<UserMedicalCenter>();
		for (MedicalCenterDto mCenter : userMedicalCentersDto.getMedicalCenters()) {
			MedicalCenter mSpeEnt = new MedicalCenter(mCenter);
			UserMedicalCenter uMedCenter = this.userMedicalCenterRepository
					.findByUserDoctorAndMedicalCenter(user, mSpeEnt);
			if (uMedCenter == null) {
				// creamos
				uMedCenter = new UserMedicalCenter();
				uMedCenter.setUserDoctor(user);
				uMedCenter.setMedicalCenter(mSpeEnt);
				uMedCenter = userMedicalCenterRepository.save(uMedCenter);
				saved.add(uMedCenter);
			} else {
				saved.add(uMedCenter);
			}
		}

		List<UserMedicalCenter> exists = this.userMedicalCenterRepository.findByUserDoctor(user);
		for (UserMedicalCenter exist : exists) {
			if (!saved.contains(exist)) {
				this.userMedicalCenterRepository.deleteById(exist.getId());
			}
		}

		return userMedicalCentersDto;
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