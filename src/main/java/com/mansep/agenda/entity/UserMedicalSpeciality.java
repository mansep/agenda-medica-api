package com.mansep.agenda.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mansep.agenda.dto.UserMedicalSpecialityDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;

@Entity
public class UserMedicalSpeciality extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MedicalSpeciality medicalSpeciality;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User userDoctor;

    public UserMedicalSpeciality() {
    }

    public UserMedicalSpeciality(UserMedicalSpecialityDto userMedicalSpeciality) {
        this.setId(userMedicalSpeciality.getId());
        setUserDoctor(userMedicalSpeciality.getUserDoctor());
        setCreatedAt(userMedicalSpeciality.getCreatedAt());
        setUpdatedAt(userMedicalSpeciality.getUpdatedAt());
        this.setStatus(userMedicalSpeciality.getStatus());
    }

    public UserMedicalSpecialityDto toDto() {
        UserMedicalSpecialityDto userMedicalSpeciality = new UserMedicalSpecialityDto();
        userMedicalSpeciality.setId(this.getId());
        userMedicalSpeciality.setStatus(this.getStatus());
        userMedicalSpeciality.setUserDoctor(userDoctor);
        userMedicalSpeciality.setMedicalSpeciality(medicalSpeciality);
        return userMedicalSpeciality;
    }

    public MedicalSpeciality getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(MedicalSpeciality medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }

}