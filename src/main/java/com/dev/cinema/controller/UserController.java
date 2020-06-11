package com.dev.cinema.controller;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(email);
        userResponseDto.setName(userService.findByEmail(email).get().getName());
        return userResponseDto;
    }
}
