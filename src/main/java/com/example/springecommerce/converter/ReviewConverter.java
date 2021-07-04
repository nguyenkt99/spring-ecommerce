package com.example.springecommerce.converter;

import com.example.springecommerce.dto.ReviewDTO;
import com.example.springecommerce.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public Review toEntity(ReviewDTO reviewDTO) {
        Review entity = new Review();
        entity.setRating(reviewDTO.getRating());
        entity.setDescription(reviewDTO.getDescription());
        return entity;
    }

    public ReviewDTO toDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setDescription(review.getDescription());
        reviewDTO.setCustomerId(review.getCustomer().getId());
        reviewDTO.setProductId(review.getProduct().getId());
        return reviewDTO;
    }


}
