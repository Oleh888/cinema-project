package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class MovieRequestDto {
    @NotNull(message = "The title can not be null")
    private String title;
    @NotNull(message = "The description can not be null")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
