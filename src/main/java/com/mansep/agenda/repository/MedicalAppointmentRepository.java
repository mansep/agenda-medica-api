package com.mansep.agenda.repository;

import java.util.Date;

import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalOffice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    MedicalAppointment findByScheduleAndOffice(Date schedule, MedicalOffice mOffice);
}