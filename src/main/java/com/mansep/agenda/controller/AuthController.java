package com.mansep.agenda.controller;

import com.mansep.agenda.dto.auth.AuthLoginDto;
import com.mansep.agenda.dto.auth.AuthResponseDto;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.ForbiddenException;
import com.mansep.agenda.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    AuthService authService;

    @PostMapping("/login")

    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthLoginDto login) {
        try {
            AuthResponseDto authDto = authService.login(login);
            return ResponseEntity.ok(authDto);
        } catch (ForbiddenException e) {
            LOGGER.error("Error al iniciar sesión", e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error al iniciar sesión", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserDto user) {
        try {
            return ResponseEntity.ok(authService.register(user));
        } catch (BadRequestException e) {
            LOGGER.error("Error al crear usuario", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Error al crear usuario", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}