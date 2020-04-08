package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalAppointmentReserved;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.User;

public class MedicalAppointmentReservedDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MedicalAppointment medicalAppointment;
    private User user;

    @Override
    public String toString() {
        return "MedicalAppointmentReservedDto [id=" + this.getId() + "]";
    }

    public static List<MedicalAppointmentReservedDto> toListDto(
            List<MedicalAppointmentReserved> mAppointmentReserveds) {
        List<MedicalAppointmentReservedDto> lu = new ArrayList<MedicalAppointmentReservedDto>();
        for (MedicalAppointmentReserved mAppointmentReserved : mAppointmentReserveds) {
            lu.add(mAppointmentReserved.toDto());
        }
        return lu;
    }

    public MedicalAppointment getMedicalAppointment() {
        return medicalAppointment;
    }

    public void setMedicalAppointment(MedicalAppointment medicalAppointment) {
        this.medicalAppointment = medicalAppointment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}