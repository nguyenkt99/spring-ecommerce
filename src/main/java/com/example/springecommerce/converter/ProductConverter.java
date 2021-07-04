package com.example.springecommerce.converter;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setUnit(productDTO.getUnit());
        product.setStatus(productDTO.getStatus());
        return product;
    }

    public Product toEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setUnit(productDTO.getUnit());
        product.setStatus(productDTO.getStatus());
        return product;
    }

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setUnit(product.getUnit());
        productDTO.setStatus(product.getStatus());
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }


}
