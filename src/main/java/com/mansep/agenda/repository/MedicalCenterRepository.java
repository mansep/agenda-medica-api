package com.mansep.agenda.repository;

import com.mansep.agenda.entity.MedicalCenter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Long> {
    MedicalCenter findByCode(String code);;
}