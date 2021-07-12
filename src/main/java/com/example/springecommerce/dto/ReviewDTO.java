package com.example.springecommerce.dto;

import com.example.springecommerce.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;

    @NotNull
    private Integer rating;

    @NotBlank
    @Size(min = 20, max = 100)
    private String description;

    private LocalDateTime createdDate;

    @NotNull
    private Long userId;

    private Long productId;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.description = review.getDescription();
        this.createdDate = review.getCreatedDate();
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
