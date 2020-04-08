package com.mansep.agenda.entity;

import javax.persistence.Column;
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

    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 10, unique = true, nullable = false)
    private String code;
    @Column(length = 200, nullable = false)
    private String address;
    @Column(length = 20, nullable = true)
    private String phone;
    @Column(length = 200, nullable = true)
    private String email;

    public MedicalCenter() {
    }

    public MedicalCenter(MedicalCenterDto mCenter) {
        this.setId(mCenter.getId());
        setName(mCenter.getName());
        setCode(mCenter.getCode());
        setAddress(mCenter.getAddress());
        setPhone(mCenter.getPhone());
        setEmail(mCenter.getEmail());
        setCreatedAt(mCenter.getCreatedAt());
        setUpdatedAt(mCenter.getUpdatedAt());
        this.setStatus(mCenter.getStatus());
    }

    public MedicalCenterDto toDto() {
        MedicalCenterDto mCenter = new MedicalCenterDto();
        mCenter.setId(this.getId());
        mCenter.setStatus(this.getStatus());
        mCenter.setName(name);
        mCenter.setCode(code);
        mCenter.setAddress(address);
        mCenter.setPhone(phone);
        mCenter.setEmail(email);
        return mCenter;
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