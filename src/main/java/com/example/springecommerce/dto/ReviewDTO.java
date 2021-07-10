package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Integer rating;
    private String description;
    private String createdDate;
    private Long userId;
    private Long productId;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.description = review.getDescription();
        this.createdDate = review.getCreatedDate().toString();
        this.userId = review.getUser().getId();
        this.productId = review.getProduct().getId();
    }

    public Review toEntity() {
        Review review = new Review();
        review.setRating(this.rating);
        review.setDescription(this.description);
        return review;
    }
}
