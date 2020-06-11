package com.dev.cinema.controller;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/register")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public User register(@RequestBody UserRequestDto userRequestDto) {
        return authenticationService.register(userRequestDto.getName(),
                userRequestDto.getEmail(), userRequestDto.getPassword());
    }
}
