package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.UserMedicalSpeciality;
import com.mansep.agenda.entity.MedicalSpeciality;
import com.mansep.agenda.entity.User;

public class UserMedicalSpecialityDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MedicalSpeciality medicalSpeciality;
    private User userDoctor;

    @Override
    public String toString() {
        return "UserMedicalSpecialityDto [id=" + this.getId() + "]";
    }

    public static List<UserMedicalSpecialityDto> toListDto(List<UserMedicalSpeciality> userMedicalSpecialitys) {
        List<UserMedicalSpecialityDto> lu = new ArrayList<UserMedicalSpecialityDto>();
        for (UserMedicalSpeciality userMedicalSpeciality : userMedicalSpecialitys) {
            lu.add(userMedicalSpeciality.toDto());
        }
        return lu;
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