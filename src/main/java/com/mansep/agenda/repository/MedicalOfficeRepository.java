package com.mansep.agenda.repository;

import com.mansep.agenda.entity.MedicalOffice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalOfficeRepository extends JpaRepository<MedicalOffice, Long> {
    MedicalOffice findByCode(String code);;
}