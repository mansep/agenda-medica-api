package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.UserMedicalCenter;
import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.entity.User;

public class UserMedicalCenterDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MedicalCenter medicalCenter;
    private User userDoctor;

    @Override
    public String toString() {
        return "UserMedicalCenterDto [id=" + this.getId() + "]";
    }

    public static List<UserMedicalCenterDto> toListDto(List<UserMedicalCenter> userMedicalCenters) {
        List<UserMedicalCenterDto> lu = new ArrayList<UserMedicalCenterDto>();
        for (UserMedicalCenter userMedicalCenter : userMedicalCenters) {
            lu.add(userMedicalCenter.toDto());
        }
        return lu;
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