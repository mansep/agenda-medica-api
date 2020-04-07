package com.mansep.agenda.entity;

import javax.persistence.Entity;

import com.mansep.agenda.dto.MedicalSpecialityDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;

@Entity
public class MedicalSpeciality extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String code;

    public MedicalSpeciality() {
    }

    public MedicalSpeciality(MedicalSpecialityDto mSpeciality) {
        this.setId(mSpeciality.getId());
        setName(mSpeciality.getName());
        setCode(mSpeciality.getCode());
        setCreatedAt(mSpeciality.getCreatedAt());
        setUpdatedAt(mSpeciality.getUpdatedAt());
        this.setStatus(mSpeciality.getStatus());
    }


    public MedicalSpecialityDto toDto() {
        MedicalSpecialityDto mSpeciality = new MedicalSpecialityDto();
        mSpeciality.setName(name);
        mSpeciality.setCode(code);
        mSpeciality.setId(this.getId());
        mSpeciality.setStatus(this.getStatus());
        return mSpeciality;
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