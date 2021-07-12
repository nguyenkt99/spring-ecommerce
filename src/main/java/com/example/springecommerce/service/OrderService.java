package com.example.springecommerce.service;

import com.example.springecommerce.dto.OrderDTO;
import com.example.springecommerce.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(long id);
    List<OrderDTO> getOrders();
    List<OrderDTO> getOrdersByUserId(long userId);
    OrderDTO handleOrder(OrderStatus orderStatus, long id);
    OrderDTO cancelOrder(long id);
}
