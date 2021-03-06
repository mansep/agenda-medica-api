package com.mansep.agenda.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.enums.Role;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.UserRepository;
import com.mansep.agenda.service.UserService;
import com.mansep.agenda.utils.Format;
import com.mansep.agenda.utils.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.apache.commons.validator.routines.EmailValidator;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {
		User user = userRepository.findByRut(rut);
		if (user == null) {
			throw new UsernameNotFoundException("No tiene permisos");
		}
		return new org.springframework.security.core.userdetails.User(user.getRut(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
		return authorities;
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	public List<User> findByRole(Role role) {
		return userRepository.findByRoleAndStatus(role, Status.ACTIVE);
	}

	public List<User> findAllDoctor() {
		return userRepository.findByRoleAndStatus(Role.DOCTOR, Status.ACTIVE);
	}

	@Override
	public User findOne(String rut) {
		return userRepository.findByRut(rut);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User update(Long id, UserDto user) throws NotFoundException {
		User editUser = this.findById(id);
		if (editUser == null) {
			throw new NotFoundException("Usuario no encontrado");
		}
		if (user.getPassword() != null) {
			if (!user.getPassword().equals("")) {
				user.setPassword(bcryptEncoder.encode(user.getPassword()));
			} else {
				user.setPassword(editUser.getPassword());
			}
		} else {
			user.setPassword(editUser.getPassword());
		}

		if (user.getRole() == null) {
			user.setRole(editUser.getRole());
		}
		user.setId(editUser.getId());
		user.setCreatedAt(editUser.getCreatedAt());
		user.setUpdatedAt(new Date());

		return userRepository.save(new User(user));
	}

	@Override
	public User updateMe(UserDto user) throws NotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return this.update(Long.parseLong(auth.getPrincipal().toString()), user);
	}

	@Override
	public User create(UserDto user) throws BadRequestException {
		User newUser = new User(user);
		newUser.setRut(Format.CleanRut(newUser.getRut()));
		newUser.setEmail(newUser.getEmail().trim().toLowerCase());
		// Validaciones
		if (!Validation.Rut(newUser.getRut())) {
			throw new BadRequestException("Rut ingresado no es válido");
		}

		if (!EmailValidator.getInstance().isValid(newUser.getEmail())) {
			throw new BadRequestException("Email ingresado no es válido");
		}

		if (newUser.getDateBirth() == null) {
			throw new BadRequestException("Debe ingresar fecha de nacimiento válida");
		}

		// Revisar duplicidad
		if (userRepository.findByRut(newUser.getRut()) != null) {
			throw new BadRequestException("Rut o Email ingresado ya están en uso");
		}

		if (userRepository.findByEmail(newUser.getEmail()) != null) {
			throw new BadRequestException("Rut o Email ingresado ya están en uso");
		}

		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		User editUser = this.findById(id);
		if (editUser == null) {
			throw new NotFoundException("Usuario no encontrado");
		}
		editUser.setStatus(Status.DELETED);
		;
		userRepository.save(editUser);
	}
}