package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalAppointment;

public class MedicalAppointmentDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date schedule;
    private MedicalOfficeDto medicalOffice;
    private MedicalSpecialityDto medicalSpeciality;
    private UserDto userDoctor;

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

    public MedicalOfficeDto getMedicalOffice() {
        return medicalOffice;
    }

    public void setMedicalOffice(MedicalOfficeDto medicalOffice) {
        this.medicalOffice = medicalOffice;
    }

    public UserDto getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(UserDto userDoctor) {
        this.userDoctor = userDoctor;
    }

    public MedicalSpecialityDto getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(MedicalSpecialityDto medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }

}