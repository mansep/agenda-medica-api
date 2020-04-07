package com.mansep.agenda.entity;

import javax.persistence.Entity;

import com.mansep.agenda.dto.MedicalCenterDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;

import java.io.Serializable;

@Entity
public class MedicalCenter extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;

    public MedicalCenter() {
    }

    public MedicalCenter(MedicalCenterDto mSpeciality) {
        this.setId(mSpeciality.getId());
        setName(mSpeciality.getName());
        setCode(mSpeciality.getCode());
        setAddress(mSpeciality.getAddress());
        setPhone(mSpeciality.getPhone());
        setEmail(mSpeciality.getEmail());
        setCreatedAt(mSpeciality.getCreatedAt());
        setUpdatedAt(mSpeciality.getUpdatedAt());
        this.setStatus(mSpeciality.getStatus());
    }


    public MedicalCenterDto toDto() {
        MedicalCenterDto mSpeciality = new MedicalCenterDto();
        mSpeciality.setId(this.getId());
        mSpeciality.setStatus(this.getStatus());
        mSpeciality.setName(name);
        mSpeciality.setCode(code);
        mSpeciality.setAddress(address);
        mSpeciality.setPhone(phone);
        mSpeciality.setEmail(email);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}