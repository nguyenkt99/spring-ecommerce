package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Order;
import com.example.springecommerce.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Double total;
    private OrderStatus status;
    private LocalDateTime createdDate;
    private Long userId;
    private List<OrderDetailDTO> orderDetails = new ArrayList<>();

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.name = order.getName();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.total = order.getTotal();
        this.status = order.getStatus();
        this.createdDate = order.getCreatedDate();
//        this.userId = order.getUser().getId();
        this.orderDetails = order.getOrderDetails().stream().map(OrderDetailDTO::new).collect(Collectors.toList());
    }

    public Order toEntity() {
        Order order = new Order();
        // id
        order.setName(this.name);
        order.setAddress(this.address);
        order.setPhone(this.phone);
        order.setTotal(this.total);
        order.setStatus(this.status);
        order.setCreatedDate(this.createdDate);
        // user
        return order;
    }

}
