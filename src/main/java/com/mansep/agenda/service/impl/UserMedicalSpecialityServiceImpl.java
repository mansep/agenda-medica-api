package com.mansep.agenda.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalSpecialityDto;
import com.mansep.agenda.dto.UserMedicalSpecialitiesDto;
import com.mansep.agenda.dto.UserMedicalSpecialityDto;
import com.mansep.agenda.entity.MedicalSpeciality;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.UserMedicalSpeciality;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.UserMedicalSpecialityRepository;
import com.mansep.agenda.service.UserMedicalSpecialityService;
import com.mansep.agenda.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userMedicalSpecialityService")
public class UserMedicalSpecialityServiceImpl implements UserMedicalSpecialityService {

	@Autowired
	private UserMedicalSpecialityRepository userMedicalSpecialityRepository;

	@Autowired
	private UserService userService;

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
	public UserMedicalSpeciality update(Long id, UserMedicalSpecialityDto userMedicalSpeciality)
			throws NotFoundException {
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
	public UserMedicalSpecialitiesDto saveMedicalSpecialities(UserMedicalSpecialitiesDto userMedicalSpecialitiesDto)
			throws NotFoundException {
		User user = this.userService.findById(userMedicalSpecialitiesDto.getUserDoctor().getId());

		if (user == null) {
			throw new NotFoundException("Especialidad de doctor  no encontrada");
		}
		List<UserMedicalSpeciality> saved = new ArrayList<UserMedicalSpeciality>();
		for (MedicalSpecialityDto mSpeciality : userMedicalSpecialitiesDto.getMedicalSpecialities()) {
			MedicalSpeciality mSpeEnt = new MedicalSpeciality(mSpeciality);
			UserMedicalSpeciality uMedSpeciality = this.userMedicalSpecialityRepository
					.findByUserDoctorAndMedicalSpeciality(user, mSpeEnt);
			if (uMedSpeciality == null) {
				// creamos
				uMedSpeciality = new UserMedicalSpeciality();
				uMedSpeciality.setUserDoctor(user);
				uMedSpeciality.setMedicalSpeciality(mSpeEnt);
				uMedSpeciality = userMedicalSpecialityRepository.save(uMedSpeciality);
				saved.add(uMedSpeciality);
			} else {
				saved.add(uMedSpeciality);
			}
		}

		List<UserMedicalSpeciality> exists = this.userMedicalSpecialityRepository.findByUserDoctor(user);
		for (UserMedicalSpeciality exist : exists) {
			if (!saved.contains(exist)) {
				this.userMedicalSpecialityRepository.deleteById(exist.getId());
			}
		}

		return userMedicalSpecialitiesDto;
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