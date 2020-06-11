package com.dev.cinema.controller;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.model.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class UserController {
    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return userMapper.getUserResponseDto(email);
    }
}
