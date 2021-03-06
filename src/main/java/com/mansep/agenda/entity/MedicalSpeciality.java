package com.mansep.agenda.entity;

import javax.persistence.Column;
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

    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 10, unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private Long price;

    public MedicalSpeciality() {
    }

    public MedicalSpeciality(MedicalSpecialityDto mSpeciality) {
        if (mSpeciality == null)
            return;
        this.setId(mSpeciality.getId());
        setName(mSpeciality.getName());
        setCode(mSpeciality.getCode());
        setPrice(mSpeciality.getPrice());
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
        mSpeciality.setPrice(this.getPrice());
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}