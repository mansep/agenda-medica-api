package com.mansep.agenda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.dto.auth.AuthLoginDto;
import com.mansep.agenda.dto.auth.AuthResponseDto;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.enums.Role;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.ForbiddenException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.config.TokenProvider;
import com.mansep.agenda.service.AuthService;
import com.mansep.agenda.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public AuthResponseDto login(AuthLoginDto user) throws Exception, ForbiddenException {
        try {
            User userLogged = userService.findOne(user.getRut());

            if (userLogged == null) {
                throw new ForbiddenException("Rut o password invalido");
            }
            if (userLogged.getStatus() == Status.DELETED) {
                throw new ForbiddenException("Su cuenta se encuentra inactiva, contactese con un administrador");
            }

            if (!BCrypt.checkpw(user.getPassword(), userLogged.getPassword())) {
                throw new ForbiddenException("Rut o password invalido");
            }

            final String token = tokenProvider.generateToken(userLogged);

            return new AuthResponseDto(userLogged.toDto(), token);
        } catch (NotFoundException e) {
            throw new ForbiddenException("Rut o password invalido");
        }
    }

    @Override
    public AuthResponseDto register(UserDto user) throws Exception, BadRequestException {
        user.setRole(Role.USER);
        User newUser = userService.create(user);
        return this.login(new AuthLoginDto(newUser.getRut(), user.getPassword()));

    }
}