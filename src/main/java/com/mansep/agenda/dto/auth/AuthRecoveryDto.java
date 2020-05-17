package com.mansep.agenda.dto.auth;

public class AuthRecoveryDto {
    public AuthRecoveryDto() {
    }

    public AuthRecoveryDto(String rut, String email) {
        this.rut = rut;
        this.email = email;
    }

    private String rut;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Override
    public String toString() {
        return "AuthDto [rut=" + rut + ", email=" + email + "]";
    }
}