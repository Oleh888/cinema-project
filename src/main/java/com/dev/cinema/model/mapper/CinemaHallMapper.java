package com.dev.cinema.model.mapper;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    private final CinemaHallService cinemaHallService;

    public CinemaHallMapper(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    public CinemaHall getCinemaHallFromRequestDto(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHallService.add(cinemaHall);
    }

    public List<CinemaHallResponseDto> getCinemaHallResponseDto() {
        return cinemaHallService.getAll().stream()
                .map(this::getCinemaHallResponseDto)
                .collect(Collectors.toList());
    }

    private CinemaHallResponseDto getCinemaHallResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
