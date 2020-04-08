package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.MedicalCenter;

public class MedicalCenterDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;

    @Override
    public String toString() {
        return "MedicalCenterDto [id=" + this.getId() + ", code=" + code + ", name=" + name + "]";
    }

    public static List<MedicalCenterDto> toListDto(List<MedicalCenter> mCenter) {
        List<MedicalCenterDto> lu = new ArrayList<MedicalCenterDto>();
        for (MedicalCenter mSpeciality : mCenter) {
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