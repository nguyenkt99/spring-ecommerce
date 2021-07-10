package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.OrderDTO;
import com.example.springecommerce.entity.Order;
import com.example.springecommerce.entity.OrderDetail;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.repository.UserRepository;
import com.example.springecommerce.repository.OrderRepository;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Order order = orderDTO.toEntity();
        order.setCreatedDate(new Date());
        order.setUser(userRepository.getById(orderDTO.getUserId()));
        List<OrderDetail> orderDetails = orderDTO.getOrderDetails().stream().map(orderDetailDTO -> {
            OrderDetail orderDetail = orderDetailDTO.toEntity();

            // get product to update quantity
            Product product = productRepository.getById(orderDetailDTO.getProductId());
            product.setQuantity(product.getQuantity() - orderDetailDTO.getQuantity());

            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            return orderDetail;
        }).collect(Collectors.toList());
        order.setOrderDetails(orderDetails);
        Order savedOrder = orderRepository.save(order);
        return new OrderDTO(savedOrder);
    }

    @Override
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(long userId) {
        return orderRepository.findByUserId(userId).stream().map(OrderDTO::new).collect(Collectors.toList());    }
}
