package com.example.springecommerce.controller;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.dto.ReviewDTO;
import com.example.springecommerce.service.ProductService;
import com.example.springecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product-categories/{categoryId}")
    public List<ProductDTO> getProductsByCategoryId(@PathVariable int categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable long id) {
        productDTO.setId(id);
        return productService.updateProduct(productDTO);
    }

    @GetMapping("/products/{productId}/reviews")
    public List<ReviewDTO> getReviews(@PathVariable long productId) {
        return reviewService.getReviews(productId);
    }

    @PostMapping("/products/{productId}/reviews")
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO, @PathVariable long productId) {
        reviewDTO.setProductId(productId);
        return reviewService.addReview(reviewDTO);
    }
}
