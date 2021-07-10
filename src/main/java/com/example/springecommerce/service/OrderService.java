package com.example.springecommerce.service;

import com.example.springecommerce.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    public OrderDTO saveOrder(OrderDTO orderDTO);
    public List<OrderDTO> getOrders();
    public List<OrderDTO> getOrdersByUserId(long userId);
}
