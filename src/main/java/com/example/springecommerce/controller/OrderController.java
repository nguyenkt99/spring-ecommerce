package com.example.springecommerce.controller;

import com.example.springecommerce.dto.OrderDTO;
import com.example.springecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/users/{userId}/orders")
    public List<OrderDTO> getOrdersByUserId(@PathVariable long userDetailId) {
        return orderService.getOrdersByUserId(userDetailId);
    }
}
