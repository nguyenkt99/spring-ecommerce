package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Product;
import com.example.springecommerce.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Double price;

    @NotBlank
    private String unit;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotBlank
    private String description;

    @NotNull
    private ProductStatus status;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @NotNull
    private Integer categoryId;

    @Size(min = 1, max = 3)
    private List<ImageDTO> images = new ArrayList<>();

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.unit = product.getUnit();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.status = product.getStatus();
        this.createdDate = product.getCreatedDate();
        this.updatedDate = product.getUpdatedDate();
        this.categoryId = product.getCategory().getId();
        this.images = product.getImages().stream().map(ImageDTO::new).collect(Collectors.toList());
    }

    public Product toEntity() {
        Product product = new Product();
        if(this.id != null) { // must has id to update
            product.setId(this.id);
        }
        product.setName(this.name);
        product.setPrice(this.price);
        product.setUnit(this.unit);
        product.setQuantity(this.quantity);
        product.setDescription(this.description);
        product.setStatus(this.status);
        return product;
    }
}
