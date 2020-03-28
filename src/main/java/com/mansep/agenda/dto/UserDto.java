package com.mansep.agenda.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mansep.agenda.entity.Role;
import com.mansep.agenda.entity.User;

public class UserDto {
    private long id;
    private String rut;
    private String password;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private Set<Role> roles;
    
    public long getId() {
        return id;
    }

    public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", rut=" + rut + ", nombres=" + nombres + ", apellidos=" + apellidos + "]";
    }

    public static List<UserDto> toListDto(List<User> users) {
        List<UserDto> lu = new ArrayList<UserDto>();
        for (User user : users) {
            lu.add(user.toDto());
        }
        return lu;
    }
}