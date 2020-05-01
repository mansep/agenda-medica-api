package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.enums.Role;
import com.mansep.agenda.entity.enums.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByRut(String rut);

    User findByRutAndStatus(String rut, Status status);

    User findByEmail(String email);

    User findByEmailAndStatus(String email, Status status);

    List<User> findByStatus(Status status);

    @Query("select u from User u " 
            + "left join fetch u.userMedicalSpecialities "
            + "left join fetch u.userMedicalCenters " 
            + "where u.status =:estado and u.role=:rol")
    List<User> findByRoleAndStatus(@Param("rol") Role role, @Param("estado") Status status);
}