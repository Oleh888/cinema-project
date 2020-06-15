package com.dev.cinema.model.dto;

import com.dev.cinema.security.EmailValidation;
import com.dev.cinema.security.PasswordValidation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@PasswordValidation
public class UserRequestDto {
    @NotNull
    private String name;
    @NotNull
    @Min(4)
    @EmailValidation
    private String email;
    @NotNull
    @Min(6)
    private String password;
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
