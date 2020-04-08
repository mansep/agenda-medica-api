package com.mansep.agenda.repository;

import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalAppointmentReserved;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAppointmentReservedRepository extends JpaRepository<MedicalAppointmentReserved, Long> {
    MedicalAppointmentReserved findByMedicalAppointment(MedicalAppointment medicalAppointment);
}