package com.dev.cinema.controller;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.model.mapper.CinemaHallMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cinema-halls")
public class CinemaHallController {
    private final CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public CinemaHall addCinemaHall(@RequestBody @Valid CinemaHallRequestDto cinemaHallRequestDto) {
        return cinemaHallMapper.getCinemaHallFromRequestDto(cinemaHallRequestDto);
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallMapper.getCinemaHallResponseDto();
    }
}
