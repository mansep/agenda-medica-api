package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.entity.User;

public class MedicalAppointmentDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date schedule;
    private MedicalOffice medicalOffice;
    private User userDoctor;

    @Override
    public String toString() {
        return "MedicalAppointmentDto [id=" + this.getId() + ", schedule=" + schedule.toString() + "]";
    }

    public static List<MedicalAppointmentDto> toListDto(List<MedicalAppointment> mAppointments) {
        List<MedicalAppointmentDto> lu = new ArrayList<MedicalAppointmentDto>();
        for (MedicalAppointment mAppointment : mAppointments) {
            lu.add(mAppointment.toDto());
        }
        return lu;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public MedicalOffice getMedicalOffice() {
        return medicalOffice;
    }

    public void setMedicalOffice(MedicalOffice medicalOffice) {
        this.medicalOffice = medicalOffice;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }

}