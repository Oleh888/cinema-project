package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class CinemaHallRequestDto {
    @NotNull(message = "The capacity can not be null")
    private int capacity;
    @NotNull(message = "The description can not be null")
    private String description;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
