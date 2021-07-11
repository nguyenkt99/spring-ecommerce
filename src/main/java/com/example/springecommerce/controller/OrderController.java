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

    @GetMapping("/orders/{id}")
    public OrderDTO getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/users/{userId}/orders")
    public List<OrderDTO> getOrdersByUserId(@PathVariable long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PatchMapping("/orders/{id}")
    public OrderDTO confirmOrder(@RequestBody OrderDTO orderDTO, @PathVariable long id) {
        orderDTO.setId(id);
        return orderService.confirmOrder(orderDTO);
    }

}
