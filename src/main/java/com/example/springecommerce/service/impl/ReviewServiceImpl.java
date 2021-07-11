package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.ReviewDTO;
import com.example.springecommerce.entity.User;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.entity.Review;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.repository.ReviewRepository;
import com.example.springecommerce.repository.UserRepository;
import com.example.springecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Product product = productRepository.getById(reviewDTO.getProductId());
        User user = userRepository.getById(reviewDTO.getUserId());
        Review review = reviewDTO.toEntity();
        review.setCreatedDate(LocalDateTime.now());
        review.setProduct(product);
        review.setUser(user);

        Review savedReview = reviewRepository.save(review);
        return new ReviewDTO(savedReview);
    }

    @Override
    public List<ReviewDTO> getReviews(long productId) {
        List<Review> reviews = reviewRepository.findOneByProductId(productId);
        return reviews.stream().map(ReviewDTO::new).collect(Collectors.toList());
    }
}
