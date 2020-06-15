package com.dev.cinema.model.dto;

import com.dev.cinema.security.EmailValidation;
import com.dev.cinema.security.PasswordValidation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@PasswordValidation
public class UserRequestDto {
    @NotNull(message = "The userName can not be null")
    private String name;
    @NotNull(message = "The email can not be null")
    @Min(4)
    @EmailValidation(message = "Your email is not valid")
    private String email;
    @NotNull(message = "The password can not be null")
    @Min(6)
    private String password;
    @NotNull(message = "The repeatPassword can not be null")
    @Min(6)
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
