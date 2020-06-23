package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> getOrderHistory(User user);
}
