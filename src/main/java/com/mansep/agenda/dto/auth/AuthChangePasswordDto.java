package com.mansep.agenda.dto.auth;

import java.io.Serializable;

public class AuthChangePasswordDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String passwordCurrent;
    private String passwordNew;

    public AuthChangePasswordDto() {
    }

    public AuthChangePasswordDto(String passwordCurrent, String passwordNew) {
        this.passwordCurrent = passwordCurrent;
        this.passwordNew = passwordNew;
    }
    @Override
    public String toString() {
        return "AuthChangePasswordDto []";
    }

    public String getPasswordCurrent() {
        return passwordCurrent;
    }

    public void setPasswordCurrent(String passwordCurrent) {
        this.passwordCurrent = passwordCurrent;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
}