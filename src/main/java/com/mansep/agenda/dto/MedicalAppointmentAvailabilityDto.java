package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalAppointment;

public class MedicalAppointmentAvailabilityDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date schedule;
    private MedicalOfficeDto medicalOffice;
    private UserDto userDoctor;
    private boolean availability;

    @Override
    public String toString() {
        return "MedicalAppointmentAvailabilityDto [id=" + this.getId() + ", scheduleFrom=" + schedule.toString() + "]";
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

    public MedicalOfficeDto getMedicalOffice() {
        return medicalOffice;
    }

    public void setMedicalOffice(MedicalOfficeDto meedicalOffice) {
        this.medicalOffice = meedicalOffice;
    }

    public UserDto getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(UserDto userDoctor) {
        this.userDoctor = userDoctor;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


}