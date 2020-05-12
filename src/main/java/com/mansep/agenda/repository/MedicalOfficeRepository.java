package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.MedicalBuilding;
import com.mansep.agenda.entity.MedicalOffice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalOfficeRepository extends JpaRepository<MedicalOffice, Long> {
    MedicalOffice findByCode(String code);

    List<MedicalOffice> findByMedicalBuilding(MedicalBuilding medicalBuilding);
}