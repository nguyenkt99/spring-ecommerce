package com.example.springecommerce.dto;

import com.example.springecommerce.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long productId;
    private Double price;
    private Integer quantity;
//    private Long orderId;

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.productId = orderDetail.getProduct().getId();
        this.price = orderDetail.getPrice();
        this.quantity = orderDetail.getQuantity();
    }

    public OrderDetail toEntity() {
        OrderDetail orderDetail = new OrderDetail();
        // id
        orderDetail.setPrice(this.price);
        orderDetail.setQuantity(this.quantity);
        // order
        return orderDetail;
    }
}
