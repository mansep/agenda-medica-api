package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalBuilding;
import com.mansep.agenda.entity.MedicalCenter;

public class MedicalBuildingDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String code;
    private MedicalCenter medicalCenter;

    @Override
    public String toString() {
        return "MedicalBuildingDto [id=" + this.getId() + ", code=" + code + ", name=" + name + "]";
    }

    public static List<MedicalBuildingDto> toListDto(List<MedicalBuilding> mBuildings) {
        List<MedicalBuildingDto> lu = new ArrayList<MedicalBuildingDto>();
        for (MedicalBuilding mBuilding : mBuildings) {
            lu.add(mBuilding.toDto());
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

    public MedicalCenter getMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(MedicalCenter medicalCenter) {
        this.medicalCenter = medicalCenter;
    }
}