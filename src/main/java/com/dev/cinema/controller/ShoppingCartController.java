package com.dev.cinema.controller;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.mapper.ShoppingCartMapper;
import com.dev.cinema.service.UserService;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartMapper shoppingCartMapper, UserService userService) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.userService = userService;
    }

    @PostMapping(value = "/add-movie-session")
    public void addMovieSession(@RequestParam Long movieSessionId,
                                @RequestParam String email) {
        shoppingCartMapper.addMovieSession(movieSessionId, email);
    }

    @GetMapping(value = "/by-user")
    public List<TicketResponseDto> getByUser(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName()).get();
        return shoppingCartMapper.getTicketResponseDtoByUserId(user.getId());
    }
}
