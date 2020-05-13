package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.MedicalAppointmentView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicalAppointmentViewRepository extends JpaRepository<MedicalAppointmentView, Long> {

    String query = "SELECT ID, DOCTOR_RUT, DOCTOR_ID, DOCTOR_NAME, DOCTOR_LAST_NAME, SPECIALITY_ID, SPECIALITY_CODE, SPECIALITY_NAME, SCHEDULE, "
            + " OFFICE_ID, OFFICE_CODE, OFFICE_NAME, OFFICE_FLOOR, BUILDING_ID, BUILDING_NAME, BUILDING_CODE, "
            + " CENTER_ID, CENTER_NAME, CENTER_CODE, CENTER_ADDRESS, CENTER_EMAIL, CENTER_PHONE "
            + " FROM medical_appointment_view WHERE SPECIALITY_ID = :specialityId AND CENTER_ID = :centerId";

    @Query(value = query, nativeQuery = true)
    List<MedicalAppointmentView> findBySpecialityIdAndCenterId(@Param("specialityId") Long specialityId,
            @Param("centerId") Long centerId);
}