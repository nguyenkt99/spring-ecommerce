package com.example.springecommerce.service;

import com.example.springecommerce.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(long id);
    List<OrderDTO> getOrders();
    List<OrderDTO> getOrdersByUserId(long userId);
    OrderDTO confirmOrder(OrderDTO orderDTO);
}
