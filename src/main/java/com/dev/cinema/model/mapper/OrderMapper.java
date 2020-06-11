package com.dev.cinema.model.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final UserService userService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderMapper(UserService userService,
                       OrderService orderService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    public Order getOrderFromUserId(Long userId) {
        User user = userService.getUserById(userId);
        return orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
    }

    public List<TicketResponseDto> getTicketResponseDtoByUserId(Long userId) {
        return orderService.getOrderHistory(userService.getUserById(userId)).stream()
                .flatMap(order -> order.getTickets().stream())
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
