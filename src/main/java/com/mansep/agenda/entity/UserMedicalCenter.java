package com.mansep.agenda.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mansep.agenda.dto.UserMedicalCenterDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;

@Entity
public class UserMedicalCenter extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalCenter medicalCenter;
    @ManyToOne(fetch = FetchType.LAZY)
    private User userDoctor;

    public UserMedicalCenter() {
    }

    public UserMedicalCenter(UserMedicalCenterDto userMedicalCenter) {
        if (userMedicalCenter == null)
            return;
        this.setId(userMedicalCenter.getId());
        setUserDoctor(userMedicalCenter.getUserDoctor());
        setCreatedAt(userMedicalCenter.getCreatedAt());
        setUpdatedAt(userMedicalCenter.getUpdatedAt());
        this.setStatus(userMedicalCenter.getStatus());
    }

    public UserMedicalCenterDto toDto() {
        UserMedicalCenterDto userMedicalCenter = new UserMedicalCenterDto();
        userMedicalCenter.setId(this.getId());
        userMedicalCenter.setStatus(this.getStatus());
        userMedicalCenter.setUserDoctor(userDoctor);
        if (medicalCenter != null) {
            userMedicalCenter.setMedicalCenter(medicalCenter.toDto());
        }
        return userMedicalCenter;
    }

    public MedicalCenter getMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(MedicalCenter medicalCenter) {
        this.medicalCenter = medicalCenter;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }

}