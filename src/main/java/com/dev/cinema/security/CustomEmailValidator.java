package com.dev.cinema.security;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;

public class CustomEmailValidator implements ConstraintValidator<EmailValidation, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}
