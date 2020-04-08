package com.mansep.agenda.repository;

import com.mansep.agenda.entity.MedicalBuilding;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalBuildingRepository extends JpaRepository<MedicalBuilding, Long> {
    MedicalBuilding findByCode(String code);;
}