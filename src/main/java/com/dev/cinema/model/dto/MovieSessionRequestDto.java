package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull(message = "The time can not be null")
    private String showTime;
    @NotNull(message = "The movieId can not be null")
    private Long movieId;
    @NotNull(message = "The cinemaHall can not be null")
    private Long cinemaHallId;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
