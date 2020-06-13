package com.dev.cinema.controller;

import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.mapper.ShoppingCartMapper;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping(value = "/add-movie-session")
    public void addMovieSession(@RequestParam Long movieSessionId,
                                @RequestParam String email) {
        shoppingCartMapper.addMovieSession(movieSessionId, email);
    }

    @GetMapping(value = "/by-user")
    public List<TicketResponseDto> getByUser(@RequestParam Long userId) {
        return shoppingCartMapper.getTicketResponseDtoByUserId(userId);
    }
}
