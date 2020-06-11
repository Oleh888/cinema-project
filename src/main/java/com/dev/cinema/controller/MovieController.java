package com.dev.cinema.controller;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.model.mapper.MovieMapper;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
    private final MovieMapper movieMapper;

    public MovieController(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @PostMapping(value = "/add")
    public Movie addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return movieMapper.getMovieFromRequestDto(movieRequestDto);
    }

    @GetMapping(value = "/all")
    public List<MovieResponseDto> getAllMovies() {
        return movieMapper.getMovieResponseDto();
    }
}
