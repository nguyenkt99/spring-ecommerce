package com.example.springecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private Boolean status;
    private Integer categoryId;
    private List<ImageDTO> images = new ArrayList<>();
//    private List<ReviewDTO> reviews = new ArrayList<>();

}
