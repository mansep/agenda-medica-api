package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.UserMedicalCenter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicalCenterRepository extends JpaRepository<UserMedicalCenter, Long> {
    List<UserMedicalCenter> findByMedicalCenter(MedicalCenter medicalCenter);
    List<UserMedicalCenter> findByUser(User userDoctor);
}