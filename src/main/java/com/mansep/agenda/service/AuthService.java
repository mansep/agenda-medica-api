package com.mansep.agenda.service;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.dto.auth.AuthLoginDto;
import com.mansep.agenda.dto.auth.AuthResponseDto;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.ForbiddenException;

public interface AuthService {

    AuthResponseDto login(AuthLoginDto user) throws Exception, ForbiddenException;
    AuthResponseDto register(UserDto user) throws Exception, BadRequestException;
}