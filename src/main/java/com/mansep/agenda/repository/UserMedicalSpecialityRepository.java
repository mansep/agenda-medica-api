package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.MedicalSpeciality;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.UserMedicalSpeciality;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicalSpecialityRepository extends JpaRepository<UserMedicalSpeciality, Long> {
    List<UserMedicalSpeciality> findByMedicalSpeciality(MedicalSpeciality medicalSpeciality);
    List<UserMedicalSpeciality> findByUser(User userDoctor);
}