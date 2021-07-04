package com.example.springecommerce.service;

import com.example.springecommerce.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public ReviewDTO addReview(ReviewDTO reviewDTO);
    public List<ReviewDTO> getReviews(long productId);
}
