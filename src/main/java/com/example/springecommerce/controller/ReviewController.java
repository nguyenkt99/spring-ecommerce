package com.example.springecommerce.controller;

import com.example.springecommerce.dto.ReviewDTO;
import com.example.springecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews/")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/{productId}")
    public List<ReviewDTO> getReviews(@PathVariable long productId) {
        return reviewService.getReviews(productId);
    }

    @PostMapping("/{productId}/{customerId}")
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO, @PathVariable long productId, @PathVariable long customerId) {
        reviewDTO.setProductId(productId);
        reviewDTO.setCustomerId(customerId);
        return reviewService.addReview(reviewDTO);
    }
}
