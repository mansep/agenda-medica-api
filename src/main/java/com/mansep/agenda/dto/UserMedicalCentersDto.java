package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.entity.UserMedicalCenter;
import com.mansep.agenda.entity.User;

public class UserMedicalCentersDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<MedicalCenterDto> medicalCenters;
    private User userDoctor;

    @Override
    public String toString() {
        return "UserMedicalCentersDto []";
    }

    public static List<UserMedicalCenterDto> toListDto(List<UserMedicalCenter> userMedicalCenters) {
        List<UserMedicalCenterDto> lu = new ArrayList<UserMedicalCenterDto>();
        for (UserMedicalCenter userMedicalCenter : userMedicalCenters) {
            lu.add(userMedicalCenter.toDto());
        }
        return lu;
    }

    public List<MedicalCenterDto> getMedicalCenters() {
        return medicalCenters;
    }

    public void setMedicalCenters(List<MedicalCenterDto> medicalCenters) {
        this.medicalCenters = medicalCenters;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }

}