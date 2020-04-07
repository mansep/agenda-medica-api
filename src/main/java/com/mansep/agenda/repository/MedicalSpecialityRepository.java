package com.mansep.agenda.repository;

import com.mansep.agenda.entity.MedicalSpeciality;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalSpecialityRepository extends JpaRepository<MedicalSpeciality, Long> {
    MedicalSpeciality findByCode(String code);;
}