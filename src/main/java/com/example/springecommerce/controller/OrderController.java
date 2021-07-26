package com.example.springecommerce.controller;

import com.example.springecommerce.dto.OrderDTO;
import com.example.springecommerce.entity.OrderStatus;
import com.example.springecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public OrderDTO addOrder(@Valid @RequestBody OrderDTO orderDTO) {
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

    @GetMapping("/users/{userId}/orders/{orderId}")
    public OrderDTO getOrderByUserIdAndOrderId(@PathVariable long userId, @PathVariable long orderId) {
        return orderService.getOrderByUserIdAndOrderId(userId, orderId);
    }

    @PutMapping("/orders/{id}")
    public OrderDTO handleOrder(@Valid @RequestBody OrderStatus orderStatus, @PathVariable long id) {
        return orderService.handleOrder(orderStatus, id);
    }

    @PutMapping("/users/{userId}/orders/{orderId}")
    public OrderDTO cancelOrder(@PathVariable long orderId) {
        return orderService.cancelOrder(orderId);
    }

}
