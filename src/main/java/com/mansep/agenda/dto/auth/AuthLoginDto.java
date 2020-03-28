package com.mansep.agenda.dto.auth;

public class AuthLoginDto {
    public AuthLoginDto() {
    }

    public AuthLoginDto(String rut, String password) {
        this.rut = rut;
        this.password = password;
    }

    private String rut;
    private String password;

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

    @Override
    public String toString() {
        return "AuthDto [rut=" + rut + ", password=" + password + "]";
    }
}