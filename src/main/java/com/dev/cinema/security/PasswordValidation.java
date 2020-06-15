package com.dev.cinema.security;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "Passwords don't equal!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
