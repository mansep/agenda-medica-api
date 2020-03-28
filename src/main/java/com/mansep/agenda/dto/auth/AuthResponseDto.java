package com.mansep.agenda.dto.auth;

import com.mansep.agenda.dto.UserDto;

public class AuthResponseDto {
    private String token;
    private UserDto user;

    public AuthResponseDto() {
    }

    public AuthResponseDto(UserDto user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserDto getUserDto() {
        return user;
    }

    public void setUserDto(UserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponseDto [token=" + token + ", user=" + user.toString() + "]";
    }
}