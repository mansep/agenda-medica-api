package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.MedicalBuilding;
import com.mansep.agenda.entity.MedicalCenter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalBuildingRepository extends JpaRepository<MedicalBuilding, Long> {
    MedicalBuilding findByCode(String code);;

    List<MedicalBuilding> findByMedicalCenter(MedicalCenter medicalCenter);
}