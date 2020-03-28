package com.mansep.agenda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.dto.auth.AuthLoginDto;
import com.mansep.agenda.dto.auth.AuthResponseDto;
import com.mansep.agenda.entity.Role;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.ForbiddenException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.RoleRepository;
import com.mansep.agenda.config.TokenProvider;
import com.mansep.agenda.service.AuthService;
import com.mansep.agenda.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public AuthResponseDto login(AuthLoginDto user) throws Exception, ForbiddenException {
        try {
            User userLogged = userService.findOne(user.getRut());
            final String token = tokenProvider.generateToken(userLogged);

            return new AuthResponseDto(userLogged.toDto(), token);
        } catch (NotFoundException e) {
            throw new ForbiddenException("Rut o password invalido");
        }
    }

    @Override
    public AuthResponseDto register(UserDto user) throws Exception, BadRequestException {
        Role role = roleRepository.findByName("USER");
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        User newUser = userService.create(user);
        return this.login(new AuthLoginDto(newUser.getRut(), user.getPassword()));

    }
}