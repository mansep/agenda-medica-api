package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalOffice;
import com.mansep.agenda.entity.MedicalBuilding;

public class MedicalOfficeDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String code;
    private String floor;
    private MedicalBuilding medicalBuilding;

    @Override
    public String toString() {
        return "MedicalOfficeDto [id=" + this.getId() + ", code=" + code + ", name=" + name + "]";
    }

    public static List<MedicalOfficeDto> toListDto(List<MedicalOffice> mOffices) {
        List<MedicalOfficeDto> lu = new ArrayList<MedicalOfficeDto>();
        for (MedicalOffice mOffice : mOffices) {
            lu.add(mOffice.toDto());
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

    public MedicalBuilding getMedicalBuilding() {
        return medicalBuilding;
    }

    public void setMedicalBuilding(MedicalBuilding medicalBuilding) {
        this.medicalBuilding = medicalBuilding;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

}