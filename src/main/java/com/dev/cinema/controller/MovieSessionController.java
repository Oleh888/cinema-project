package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.mapper.MovieSessionMapper;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie-sessions")
public class MovieSessionController {
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionMapper movieSessionMapper) {
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public MovieSession addMovieSession(
            @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        return movieSessionMapper.getMovieSessionFromRequestDto(movieSessionRequestDto);
    }

    @GetMapping(value = "/available")
    public List<MovieSessionResponseDto> getAvailableMovieSession(
            @RequestParam Long movieId, @RequestParam String localDate) {
        return movieSessionMapper.getMovieSessionResponseDto(movieId, localDate);
    }
}
