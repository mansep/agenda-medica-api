package com.mansep.agenda.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mansep.agenda.dto.MedicalAppointmentReservedDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;

@Entity
public class MedicalAppointmentReserved extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalAppointment medicalAppointment;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public MedicalAppointmentReserved() {
    }

    public MedicalAppointmentReserved(MedicalAppointmentReservedDto mAppointmentReserved) {
        this.setId(mAppointmentReserved.getId());
        setUser(mAppointmentReserved.getUser());
        setCreatedAt(mAppointmentReserved.getCreatedAt());
        setUpdatedAt(mAppointmentReserved.getUpdatedAt());
        this.setStatus(mAppointmentReserved.getStatus());
    }


    public MedicalAppointmentReservedDto toDto() {
        MedicalAppointmentReservedDto mAppointmentReserved = new MedicalAppointmentReservedDto();
        mAppointmentReserved.setId(this.getId());
        mAppointmentReserved.setStatus(this.getStatus());
        mAppointmentReserved.setUser(user);
        mAppointmentReserved.setMedicalAppointment(medicalAppointment);
        return mAppointmentReserved;
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