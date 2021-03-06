package com.mansep.agenda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mansep.agenda.dto.abstrct.AbstractBaseDto;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.enums.Role;

import java.util.*;

public class UserDto extends AbstractBaseDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String rut;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private Role role;
    private Date dateBirth;

    private Set<UserMedicalSpecialityDto> userMedicalSpecialities = new HashSet<>();
    private Set<UserMedicalCenterDto> userMedicalCenters = new HashSet<>();

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        if (rut == null) {
            return rut;
        }
        return rut.replace("-", "").replace(".", "").toUpperCase().trim();
    }

    public void setRut(String rut) {
        if (rut != null) {
            rut = rut.replace("-", "").replace(".", "").toUpperCase().trim();
        }
        this.rut = rut;
    }

    public Set<UserMedicalCenterDto> getUserMedicalCenters() {
        return userMedicalCenters;
    }

    public void setUserMedicalCenters(Set<UserMedicalCenterDto> userMedicalCenters) {
        this.userMedicalCenters = userMedicalCenters;
    }

    public Set<UserMedicalSpecialityDto> getUserMedicalSpecialities() {
        return userMedicalSpecialities;
    }

    public void setUserMedicalSpecialities(Set<UserMedicalSpecialityDto> userMedicalSpecialities) {
        this.userMedicalSpecialities = userMedicalSpecialities;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + this.getId() + ", rut=" + rut + ", name=" + name + ", lastName=" + lastName + "]";
    }

    public static List<UserDto> toListDto(List<User> users) {
        List<UserDto> lu = new ArrayList<UserDto>();
        for (User user : users) {
            lu.add(user.toDto());
        }
        return lu;
    }
}