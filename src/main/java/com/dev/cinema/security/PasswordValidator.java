package com.dev.cinema.security;

import com.dev.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, UserRequestDto> {
    @Override
    public boolean isValid(UserRequestDto userRequestDto, ConstraintValidatorContext context) {
        return userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword());
    }
}
