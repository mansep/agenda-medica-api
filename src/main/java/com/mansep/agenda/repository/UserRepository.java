package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.enums.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByRut(String rut);
    User findByRutAndStatus(String rut, Status status);
    User findByEmail(String email);
    User findByEmailAndStatus(String email, Status status);
    List<User> findByStatus(Status status);
}