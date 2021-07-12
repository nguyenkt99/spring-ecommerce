package com.example.springecommerce.dto;

import com.example.springecommerce.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long id;
//    private Long productId;
    private ProductDTO product;
    private Double price;

    private Integer quantity;

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
//        this.productId = orderDetail.getProduct().getId();
        this.product = new ProductDTO(orderDetail.getProduct());
        this.price = orderDetail.getPrice();
        this.quantity = orderDetail.getQuantity();
    }

    public OrderDetail toEntity() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPrice(this.price);
        orderDetail.setQuantity(this.quantity);
        return orderDetail;
    }
}
