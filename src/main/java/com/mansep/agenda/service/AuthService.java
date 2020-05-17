package com.mansep.agenda.service;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.dto.auth.AuthChangePasswordDto;
import com.mansep.agenda.dto.auth.AuthLoginDto;
import com.mansep.agenda.dto.auth.AuthRecoveryDto;
import com.mansep.agenda.dto.auth.AuthResponseDto;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.ForbiddenException;
import com.mansep.agenda.exception.NotFoundException;

public interface AuthService {

    AuthResponseDto login(AuthLoginDto user) throws Exception, ForbiddenException;

    AuthResponseDto register(UserDto user) throws Exception, BadRequestException;

    boolean changePassword(AuthChangePasswordDto changePassword)
            throws Exception, ForbiddenException, BadRequestException;

    AuthRecoveryDto recovery(AuthRecoveryDto recovery) throws NotFoundException, BadRequestException;
}