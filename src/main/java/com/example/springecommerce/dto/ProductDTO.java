package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Product;
import com.example.springecommerce.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String unit;
    private Integer quantity;
    private String description;
    private ProductStatus status;
    private String createdDate;
    private String updatedDate;
    private Integer categoryId;
    private List<ImageDTO> images = new ArrayList<>();
//    private List<ReviewDTO> reviews = new ArrayList<>();

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.unit = product.getUnit();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.status = product.getStatus();
        this.createdDate = product.getCreatedDate().toString();
        this.updatedDate = product.getUpdatedDate().toString();
        this.categoryId = product.getCategory().getId();
        this.images = product.getImages().stream().map(ImageDTO::new).collect(Collectors.toList());
    }

    public Product toEntity() {
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setUnit(this.unit);
        product.setQuantity(this.quantity);
        product.setDescription(this.description);
        product.setStatus(this.status);
        return product;
    }

    public Product toEntity(Product oldProduct) {
        oldProduct.setName(this.name);
        oldProduct.setPrice(this.price);
        oldProduct.setUnit(this.unit);
        oldProduct.setQuantity(this.quantity);
        oldProduct.setDescription(this.description);
        oldProduct.setStatus(this.status);
        return oldProduct;
    }

}
