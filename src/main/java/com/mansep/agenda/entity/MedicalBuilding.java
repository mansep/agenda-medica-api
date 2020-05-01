package com.mansep.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mansep.agenda.dto.MedicalBuildingDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;

@Entity
public class MedicalBuilding extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 10, unique = true, nullable = false)
    private String code;
    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalCenter medicalCenter;

    public MedicalBuilding() {
    }

    public MedicalBuilding(MedicalBuildingDto mBuilding) {
        this.setId(mBuilding.getId());
        setName(mBuilding.getName());
        setCode(mBuilding.getCode());
        setMedicalCenter(mBuilding.getMedicalCenter());
        setCreatedAt(mBuilding.getCreatedAt());
        setUpdatedAt(mBuilding.getUpdatedAt());
        this.setStatus(mBuilding.getStatus());
    }

    public MedicalBuildingDto toDto() {
        MedicalBuildingDto mBuilding = new MedicalBuildingDto();
        mBuilding.setId(this.getId());
        mBuilding.setStatus(this.getStatus());
        mBuilding.setName(name);
        mBuilding.setCode(code);
        mBuilding.setMedicalCenter(medicalCenter);
        return mBuilding;
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