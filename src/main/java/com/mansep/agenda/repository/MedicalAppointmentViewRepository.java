package com.mansep.agenda.repository;

import java.util.List;

import com.mansep.agenda.entity.MedicalAppointmentView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicalAppointmentViewRepository extends JpaRepository<MedicalAppointmentView, Long> {

        String query = "SELECT ID, RESERVED_ID, RESERVED_STATUS, DOCTOR_RUT, DOCTOR_ID, DOCTOR_NAME, DOCTOR_LAST_NAME, SPECIALITY_ID, SPECIALITY_CODE, SPECIALITY_NAME, SCHEDULE, "
                        + " OFFICE_ID, OFFICE_CODE, OFFICE_NAME, OFFICE_FLOOR, BUILDING_ID, BUILDING_NAME, BUILDING_CODE, "
                        + " CENTER_ID, CENTER_NAME, CENTER_CODE, CENTER_ADDRESS, CENTER_EMAIL, CENTER_PHONE, "
                        + " PATIENT_ID, PATIENT_RUT, PATIENT_NAME, PATIENT_LAST_NAME, PATIENT_EMAIL"
                        + " FROM medical_appointment_view";

        @Query(value = query
                        + " WHERE ifnull(RESERVED_ID, 0) = 0 AND SCHEDULE > now() AND SPECIALITY_ID = :specialityId AND CENTER_ID = :centerId", nativeQuery = true)
        List<MedicalAppointmentView> findBySpecialityIdAndCenterId(@Param("specialityId") Long specialityId,
                        @Param("centerId") Long centerId);

        @Query(value = query
                        + " WHERE ifnull(RESERVED_ID, 0) = 0 AND SCHEDULE > now() AND ID = :id", nativeQuery = true)
        MedicalAppointmentView findInViewById(@Param("id") Long medicalAppointmentId);

        @Query(value = query + " WHERE RESERVED_ID = :id", nativeQuery = true)
        MedicalAppointmentView findInViewByReservedId(@Param("id") Long medicalAppointmentReservedId);

        @Query(value = query + " WHERE PATIENT_ID = :id", nativeQuery = true)
        List<MedicalAppointmentView> findInViewByPatientId(@Param("id") Long userPatientId);

        @Query(value = query + " WHERE DOCTOR_ID = :id AND ifnull(PATIENT_ID, 0) != 0", nativeQuery = true)
        List<MedicalAppointmentView> findInViewByDoctorId(@Param("id") Long userDoctorId);

        // @Query(value = query + " WHERE PATIENT_ID = :id AND SCHEDULE > now() ",
        // nativeQuery = true)
        // List<MedicalAppointmentView> findInViewByPatientId(@Param("id") Long
        // userPatientId);

        // @Query(value = query
        // + " WHERE DOCTOR_ID = :id AND ifnull(PATIENT_ID, 0) != 0 AND SCHEDULE >
        // now()", nativeQuery = true)
        // List<MedicalAppointmentView> findInViewByDoctorId(@Param("id") Long
        // userDoctorId);

        // @Query(value = query + " WHERE PATIENT_ID = :id", nativeQuery = true)
        // List<MedicalAppointmentView> findInViewHistoryByPatientId(@Param("id") Long
        // userPatientId);

        // @Query(value = query + " WHERE DOCTOR_ID = :id AND ifnull(PATIENT_ID, 0) !=
        // 0", nativeQuery = true)
        // List<MedicalAppointmentView> findInViewHistoryByDoctorId(@Param("id") Long
        // userDoctorId);
}