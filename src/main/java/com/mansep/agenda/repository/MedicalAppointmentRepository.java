package com.mansep.agenda.repository;

import java.util.Date;

import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    MedicalAppointment findByScheduleAndMedicalOffice(Date schedule, MedicalOffice mOffice);

    MedicalAppointment findByScheduleAndMedicalOfficeAndUserDoctor(Date schedule, MedicalOffice mOffice,
            User userDoctor);
}