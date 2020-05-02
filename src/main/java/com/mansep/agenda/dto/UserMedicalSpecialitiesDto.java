package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.entity.UserMedicalSpeciality;
import com.mansep.agenda.entity.User;

public class UserMedicalSpecialitiesDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<MedicalSpecialityDto> medicalSpecialities;
    private User userDoctor;

    @Override
    public String toString() {
        return "UserMedicalSpecialitiesDto []";
    }

    public static List<UserMedicalSpecialityDto> toListDto(List<UserMedicalSpeciality> userMedicalSpecialitys) {
        List<UserMedicalSpecialityDto> lu = new ArrayList<UserMedicalSpecialityDto>();
        for (UserMedicalSpeciality userMedicalSpeciality : userMedicalSpecialitys) {
            lu.add(userMedicalSpeciality.toDto());
        }
        return lu;
    }

    public List<MedicalSpecialityDto> getMedicalSpecialities() {
        return medicalSpecialities;
    }

    public void setMedicalSpecialities(List<MedicalSpecialityDto> medicalSpecialities) {
        this.medicalSpecialities = medicalSpecialities;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }

}