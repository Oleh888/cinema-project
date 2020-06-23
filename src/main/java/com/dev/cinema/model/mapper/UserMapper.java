package com.dev.cinema.model.mapper;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserService userService;

    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDto getUserResponseDto(String email) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(email);
        userResponseDto.setName(userService.getByEmail(email).get().getName());
        return userResponseDto;
    }
}
