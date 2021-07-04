package com.example.springecommerce.service.impl;

import com.example.springecommerce.converter.ReviewConverter;
import com.example.springecommerce.dto.ReviewDTO;
import com.example.springecommerce.entity.Customer;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.entity.Review;
import com.example.springecommerce.repository.CustomerRepository;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.repository.ReviewRepository;
import com.example.springecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReviewConverter reviewConverter;

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Product product = productRepository.getById(reviewDTO.getProductId());
        Customer customer = customerRepository.getById(reviewDTO.getCustomerId());
        Review review = reviewConverter.toEntity(reviewDTO);
        review.setProduct(product);
        review.setCustomer(customer);

        return reviewConverter.toDTO(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDTO> getReviews(long productId) {
        List<Review> reviews = reviewRepository.findOneByProductId(productId);
        return reviews.stream().map(review -> {
            return reviewConverter.toDTO(review);
        }).collect(Collectors.toList());

    }
}
