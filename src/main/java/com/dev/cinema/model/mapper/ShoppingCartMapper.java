package com.dev.cinema.model.mapper;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartMapper(MovieSessionService movieSessionService,
                              UserService userService, ShoppingCartService shoppingCartService) {
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    public void addMovieSession(Long movieSessionId, String email) {
        shoppingCartService.addSession(movieSessionService
                        .getMovieSessionById(movieSessionId),
                userService.findByEmail(email).get());
    }

    public List<TicketResponseDto> getTicketResponseDtoByUserId(Long userId) {
        return shoppingCartService.getByUser(userService.getUserById(userId))
                .getTickets().stream()
                .map(this::transferTicketToDto)
                .collect(Collectors.toList());
    }

    private TicketResponseDto transferTicketToDto(Ticket ticket) {
        TicketResponseDto ticketDto = new TicketResponseDto();
        ticketDto.setMovieTitle(ticket.getMovie().getTitle());
        ticketDto.setCinemaHallDescription(ticket.getCinemaHall().getDescription());
        ticketDto.setShowTime(ticket.getShowTime().toString());
        return ticketDto;
    }
}
