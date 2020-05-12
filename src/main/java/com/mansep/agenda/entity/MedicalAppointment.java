package com.mansep.agenda.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mansep.agenda.dto.MedicalAppointmentDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;
import java.util.Date;

@Entity
public class MedicalAppointment extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date schedule;
    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalOffice medicalOffice;
    @ManyToOne(fetch = FetchType.LAZY)
    private User userDoctor;

    public MedicalAppointment() {
    }

    public MedicalAppointment(MedicalAppointmentDto mAppointment) {
        if (mAppointment == null)
            return;
        this.setId(mAppointment.getId());
        setSchedule(mAppointment.getSchedule());
        setUserDoctor(new User(mAppointment.getUserDoctor()));
        setMedicalOffice(new MedicalOffice(mAppointment.getMedicalOffice()));
        setCreatedAt(mAppointment.getCreatedAt());
        setUpdatedAt(mAppointment.getUpdatedAt());
        this.setStatus(mAppointment.getStatus());
    }

    public MedicalAppointmentDto toDto() {
        MedicalAppointmentDto mAppointment = new MedicalAppointmentDto();
        mAppointment.setId(this.getId());
        mAppointment.setStatus(this.getStatus());
        mAppointment.setSchedule(schedule);
        if (userDoctor != null) {
            mAppointment.setUserDoctor(userDoctor.toDto());
        }
        if (medicalOffice != null) {
            mAppointment.setMedicalOffice(medicalOffice.toDto());
        }
        return mAppointment;
    }

    public MedicalOffice getMedicalOffice() {
        return medicalOffice;
    }

    public void setMedicalOffice(MedicalOffice medicalOffice) {
        this.medicalOffice = medicalOffice;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }

}