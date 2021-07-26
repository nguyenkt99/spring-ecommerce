package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.OrderDTO;
import com.example.springecommerce.dto.OrderDetailDTO;
import com.example.springecommerce.entity.*;
import com.example.springecommerce.exception.NotFoundException;
import com.example.springecommerce.repository.UserRepository;
import com.example.springecommerce.repository.OrderRepository;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        double totalPrice = 0;
        Order order = orderDTO.toEntity();
        order.setCreatedDate(LocalDateTime.now());
        order.setUser(userRepository.getById(orderDTO.getUserId()));

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetails()) {
            Product product = productRepository.getById(orderDetailDTO.getProduct().getId()); // get product

            if(product.getQuantity() < orderDetailDTO.getQuantity()) {
                throw new RuntimeException("Products in stock is not enough!");
            }
            product.setQuantity(product.getQuantity() - orderDetailDTO.getQuantity()); // update quantity
            totalPrice += orderDetailDTO.getQuantity() * product.getPrice().doubleValue();

            OrderDetail orderDetail = orderDetailDTO.toEntity();
            orderDetail.setPrice(product.getPrice());
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetails.add(orderDetail);
        }

        order.setStatus(OrderStatus.UNCONFIRMED);
        order.setTotal(totalPrice);
        order.setOrderDetails(orderDetails);
        Order savedOrder = orderRepository.save(order);
        return new OrderDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not exists"));
        return new OrderDTO(order);
    }

    @Override
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(long userId) {
        return orderRepository.findByUserId(userId).stream().map(OrderDTO::new).collect(Collectors.toList());    }

    @Override
    public OrderDTO getOrderByUserIdAndOrderId(long userId, long orderId) {
        Order order = orderRepository.findByUserIdAndOrderId(userId, orderId);
        if(order == null)
            throw new NotFoundException("Order not exists");
        return new OrderDTO(order);
    }

    @Override
    public OrderDTO handleOrder(OrderStatus orderStatus, long id) {
        Order order = orderRepository.getById(id);
        if(orderStatus.equals(OrderStatus.CANCELED)) {
            if(order.getStatus().equals(OrderStatus.CANCELED)) {
                throw new RuntimeException("Order has been cancelled");
            }
            for(OrderDetail o : order.getOrderDetails()) { // return product quantity
                Product p = productRepository.getById(o.getProduct().getId());
                p.setQuantity(p.getQuantity() + o.getQuantity());
            }
        }

        order.setStatus(orderStatus);
        Order savedOrder = orderRepository.save(order);
        return new OrderDTO(savedOrder);
    }

    @Override
    public OrderDTO cancelOrder(long id) {
        Order order = orderRepository.getById(id);
        if(order.getStatus().equals(OrderStatus.UNCONFIRMED)) {
            for(OrderDetail o : order.getOrderDetails()) { // return product quantity
                Product p = productRepository.getById(o.getProduct().getId());
                p.setQuantity(p.getQuantity() + o.getQuantity());
            }
            order.setStatus(OrderStatus.CANCELED);
            Order savedOrder = orderRepository.save(order);
            return new OrderDTO(savedOrder);
        } else {
            throw new RuntimeException("Order can't be canceled");
        }
    }
}
