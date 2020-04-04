package com.mansep.agenda.service;

import java.util.List;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;

public interface UserService {

    User create(UserDto user) throws BadRequestException, Exception;
    User update(Long id, UserDto user) throws NotFoundException;
    List<User> findAll();
    void delete(Long id) throws NotFoundException;
    User findOne(String rut) throws NotFoundException;
    User findById(Long id) throws NotFoundException;
}