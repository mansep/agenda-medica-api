package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalAppointmentReserved;

public class MedicalAppointmentReservedDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MedicalAppointmentDto medicalAppointment;
    private UserDto user;
    private Long price;

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

    public MedicalAppointmentDto getMedicalAppointment() {
        return medicalAppointment;
    }

    public void setMedicalAppointment(MedicalAppointmentDto medicalAppointment) {
        this.medicalAppointment = medicalAppointment;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}