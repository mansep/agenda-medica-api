package com.mansep.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mansep.agenda.dto.MedicalOfficeDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;

@Entity
public class MedicalOffice extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 10, unique = true, nullable = false)
    private String code;
    @Column(length = 20, nullable = true)
    private String floor;
    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalBuilding medicalBuilding;

    public MedicalOffice() {
    }

    public MedicalOffice(MedicalOfficeDto mOffice) {
        if (mOffice == null)
            return;
        this.setId(mOffice.getId());
        setName(mOffice.getName());
        setCode(mOffice.getCode());
        setFloor(mOffice.getFloor());
        if (mOffice.getMedicalBuilding() != null) {
            setMedicalBuilding(new MedicalBuilding(mOffice.getMedicalBuilding()));
        }
        setCreatedAt(mOffice.getCreatedAt());
        setUpdatedAt(mOffice.getUpdatedAt());
        this.setStatus(mOffice.getStatus());
    }

    public MedicalOfficeDto toDto() {
        MedicalOfficeDto mOffice = new MedicalOfficeDto();
        mOffice.setId(this.getId());
        mOffice.setStatus(this.getStatus());
        mOffice.setName(name);
        mOffice.setCode(code);
        mOffice.setFloor(floor);
        if (medicalBuilding != null) {
            mOffice.setMedicalBuilding(medicalBuilding.toDto());
        }
        return mOffice;
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