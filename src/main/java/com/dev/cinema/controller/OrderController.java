package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.mapper.OrderMapper;
import com.dev.cinema.service.UserService;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final UserService userService;

    public OrderController(OrderMapper orderMapper, UserService userService) {
        this.orderMapper = orderMapper;
        this.userService = userService;
    }

    @PostMapping(value = "/complete")
    public Order completeOrder(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName()).get();
        return orderMapper.getOrderFromUserId(user.getId());
    }

    @GetMapping
    public List<TicketResponseDto> getUserOrders(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName()).get();
        return orderMapper.getTicketResponseDtoByUserId(user.getId());
    }
}
