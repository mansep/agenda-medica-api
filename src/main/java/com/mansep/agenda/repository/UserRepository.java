package com.mansep.agenda.repository;

import com.mansep.agenda.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByRut(String rut);
    User findByEmail(String email);
}