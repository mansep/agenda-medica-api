package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalSpeciality;

public class MedicalSpecialityDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String code;

    @Override
    public String toString() {
        return "MedicalSpecialityDto [id=" + this.getId() + ", code=" + code + ", name=" + name + "]";
    }

    public static List<MedicalSpecialityDto> toListDto(List<MedicalSpeciality> mSpecialitys) {
        List<MedicalSpecialityDto> lu = new ArrayList<MedicalSpecialityDto>();
        for (MedicalSpeciality mSpeciality : mSpecialitys) {
            lu.add(mSpeciality.toDto());
        }
        return lu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}