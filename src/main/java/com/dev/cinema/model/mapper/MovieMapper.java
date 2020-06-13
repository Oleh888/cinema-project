package com.dev.cinema.model.mapper;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private final MovieService movieService;

    public MovieMapper(MovieService movieService) {
        this.movieService = movieService;
    }

    public Movie getMovieFromRequestDto(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movieService.add(movie);
    }

    public List<MovieResponseDto> getMovieResponseDto() {
        return movieService.getAll().stream()
                .map(this::transferMovieToDto)
                .collect(Collectors.toList());
    }

    private MovieResponseDto transferMovieToDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }
}
