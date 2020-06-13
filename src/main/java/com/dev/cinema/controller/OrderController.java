package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.mapper.OrderMapper;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderMapper orderMapper;

    public OrderController(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @PostMapping(value = "/complete")
    public Order completeOrder(@RequestParam Long userId) {
        return orderMapper.getOrderFromUserId(userId);
    }

    @GetMapping
    public List<TicketResponseDto> getUserOrders(@RequestParam Long userId) {
        return orderMapper.getTicketResponseDtoByUserId(userId);
    }
}
