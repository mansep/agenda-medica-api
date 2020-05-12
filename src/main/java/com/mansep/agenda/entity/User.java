package com.mansep.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.mansep.agenda.dto.UserDto;
import com.mansep.agenda.dto.UserMedicalCenterDto;
import com.mansep.agenda.dto.UserMedicalSpecialityDto;
import com.mansep.agenda.entity.abstrct.AbstractBaseEntity;
import com.mansep.agenda.entity.enums.Role;

import java.io.Serializable;
import java.util.*;

@Entity
public class User extends AbstractBaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true, length = 9)
    private String rut;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 200)
    private String lastName;
    @Column(nullable = false, unique = true, length = 200)
    private String email;
    @Column(length = 20)
    private String phone;
    @Column(length = 20)
    private String mobile;
    private Date dateBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "varchar(20) default 'USER'")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userDoctor")
    private Set<UserMedicalSpeciality> userMedicalSpecialities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userDoctor")
    private Set<UserMedicalCenter> userMedicalCenters = new HashSet<>();

    public User() {
    }

    public User(UserDto user) {
        if (user == null) return;
        Set<UserMedicalCenter> center = null;
        if (user.getUserMedicalCenters() != null) {
            center = new HashSet<UserMedicalCenter>();
            for (UserMedicalCenterDto dto : user.getUserMedicalCenters()) {
                center.add(new UserMedicalCenter(dto));
            }
        }

        Set<UserMedicalSpeciality> speciality = null;
        if (user.getUserMedicalCenters() != null) {
            speciality = new HashSet<UserMedicalSpeciality>();
            for (UserMedicalSpecialityDto dto : user.getUserMedicalSpecialities()) {
                speciality.add(new UserMedicalSpeciality(dto));
            }
        }

        this.setId(user.getId());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setName(user.getName());
        setRut(user.getRut());
        setPhone(user.getPhone());
        setMobile(user.getMobile());
        setDateBirth(user.getDateBirth());
        setPassword(user.getPassword());
        setRole(user.getRole());
        setCreatedAt(user.getCreatedAt());
        setUpdatedAt(user.getUpdatedAt());
        setUserMedicalCenters(center);
        setUserMedicalSpecialities(speciality);
        this.setStatus(user.getStatus());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDto toDto(Boolean withPassword) {
        UserDto uDto = this.toDto();
        if (withPassword) {
            uDto.setPassword(password);
        }
        return uDto;
    }

    public UserDto toDto() {

        Set<UserMedicalCenterDto> center = null;
        Set<UserMedicalSpecialityDto> speciality = null;

        if (userMedicalCenters != null) {
            center = new HashSet<UserMedicalCenterDto>();
            for (UserMedicalCenter entity : userMedicalCenters) {
                entity.setUserDoctor(null);
                center.add(entity.toDto());
            }
        }

        if (userMedicalSpecialities != null) {
            speciality = new HashSet<UserMedicalSpecialityDto>();
            for (UserMedicalSpeciality entity : userMedicalSpecialities) {
                entity.setUserDoctor(null);
                speciality.add(entity.toDto());
            }
        }

        UserDto user = new UserDto();
        user.setLastName(lastName);
        user.setEmail(email);
        user.setId(this.getId());
        user.setName(name);
        user.setRut(rut);
        user.setPhone(phone);
        user.setMobile(mobile);
        user.setRole(this.getRole());
        user.setStatus(this.getStatus());
        user.setDateBirth(dateBirth);
        user.setUserMedicalCenters(center);
        user.setUserMedicalSpecialities(speciality);
        return user;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<UserMedicalSpeciality> getUserMedicalSpecialities() {
        return userMedicalSpecialities;
    }

    public void setUserMedicalSpecialities(Set<UserMedicalSpeciality> userMedicalSpecialities) {
        this.userMedicalSpecialities = userMedicalSpecialities;
    }

    public Set<UserMedicalCenter> getUserMedicalCenters() {
        return userMedicalCenters;
    }

    public void setUserMedicalCenters(Set<UserMedicalCenter> userMedicalCenters) {
        this.userMedicalCenters = userMedicalCenters;
    }

}