package com.example.springecommerce.service;

import com.example.springecommerce.dto.OrderDTO;
import com.example.springecommerce.entity.Order;
import com.example.springecommerce.entity.OrderStatus;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.entity.User;
import com.example.springecommerce.exception.NotFoundException;
import com.example.springecommerce.repository.OrderRepository;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.repository.UserRepository;
import com.example.springecommerce.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    private List<Order> orders;
    private User user = new User();
    private OrderDTO orderDTO = new OrderDTO();


    @BeforeEach
    void setUp() {
        Order order1 = new Order();
        Order order2 = new Order();
        user.setId(1L);
        order1.setId(1L);
        order1.setName("Nguyen");
        order1.setStatus(OrderStatus.UNCONFIRMED);
        order1.setUser(user);
        order2.setId(2L);
        order2.setName("Nguyen");
        order2.setStatus(OrderStatus.UNCONFIRMED);
        order2.setUser(user);
        orders = Stream.of(order1, order2).collect(Collectors.toList());
        orderDTO = new OrderDTO(order1);
    }

    @Test
    void saveOrder_ReturnOrder_WhenOk() {
        Mockito.when(orderRepository.save(Mockito.anyObject())).thenReturn(orders.get(0));
        assertEquals(orders.get(0).getId(), orderService.saveOrder(new OrderDTO(orders.get(0))).getId());
    }

    @Test
    void getOrderById_ReturnOrder_WhenOrderExists() {
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(orders.get(0)));
        assertEquals(orders.get(0).getId(), orderService.getOrderById(Mockito.anyLong()).getId());
    }

    @Test
    void getOrderById_ThrowException_WhenNotExists() {
        NotFoundException exception = new NotFoundException("Order not exists");
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenThrow(exception);
        assertEquals("Order not exists", exception.getMessage());
    }

    @Test
    void getOrders_ReturnOrders() {
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        assertEquals(2, orderService.getOrders().size());
    }

    @Test
    void getOrdersByUserId() {
        Mockito.when(orderRepository.findByUserId(Mockito.anyLong())).thenReturn(orders);
        assertEquals(2, orderService.getOrdersByUserId(Mockito.anyLong()).size());
    }

    @Test
    void handleConfirmOrder_ReturnOrder_WhenSuccessful() {
        Mockito.when(orderRepository.getById(Mockito.anyLong())).thenReturn(orders.get(0));
        Mockito.when(orderRepository.save(Mockito.anyObject())).thenReturn(orders.get(0));
        assertEquals(OrderStatus.CONFIRMED, orderService.handleOrder(OrderStatus.CONFIRMED, Mockito.anyLong()).getStatus());
    }

    @Test
    void cancelOrder_ReturnOrder_WhenSuccessful() {
        Mockito.when(orderRepository.getById(Mockito.anyLong())).thenReturn(orders.get(0));
        Mockito.when(orderRepository.save(Mockito.anyObject())).thenReturn(orders.get(0));
        assertEquals(OrderStatus.CANCELED, orderService.cancelOrder(Mockito.anyLong()).getStatus());
    }
}