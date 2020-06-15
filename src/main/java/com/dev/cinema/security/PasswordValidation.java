package com.dev.cinema.security;

import javax.validation.Payload;

public @interface PasswordValidation {
    String message() default "Passwords don't equal!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
