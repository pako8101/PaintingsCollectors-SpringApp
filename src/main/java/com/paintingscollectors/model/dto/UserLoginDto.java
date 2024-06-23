package com.paintingscollectors.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDto {

    @NotBlank(message = "Username must not be empty!")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols!")
    private String username;
    @NotBlank(message = "Password must not be empty!")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols!")
    private String password;

    public UserLoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
