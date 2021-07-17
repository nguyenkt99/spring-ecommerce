package com.example.springecommerce.service;

import com.example.springecommerce.dto.ReviewDTO;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.entity.Review;
import com.example.springecommerce.entity.User;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.repository.ReviewRepository;
import com.example.springecommerce.repository.UserRepository;
import com.example.springecommerce.service.impl.ReviewServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ReviewServiceImplTest {
    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ReviewServiceImpl reviewService;

    private List<Review> reviews;

    @BeforeEach
    void setUp() {
        Review review1 = new Review();
        Review review2 = new Review();
        Product product = new Product();
        User user = new User();

        product.setId(1L);
        user.setId(1L);
        review1.setId(1L);
        review1.setProduct(product);
        review1.setUser(user);
        review2.setId(2L);
        review2.setProduct(product);
        review2.setUser(user);
        reviews = Stream.of(review1, review2).collect(Collectors.toList());
    }

    @Test
    void addReview() {
        ReviewDTO reviewDTO = new ReviewDTO();
        Mockito.when(reviewRepository.save(Mockito.anyObject())).thenReturn(reviews.get(0));
        Assertions.assertThat(new ReviewDTO(reviews.get(0)))
                .usingRecursiveComparison()
                .isEqualTo(reviewService.addReview(reviewDTO));
    }

    @Test
    void getReviews() {
        Mockito.when(reviewRepository.findByProductId(Mockito.anyLong())).thenReturn(reviews);
        assertEquals(2, reviewService.getReviews(Mockito.anyLong()).size());
    }
}