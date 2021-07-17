package com.example.springecommerce.service;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.entity.Category;
import com.example.springecommerce.entity.Product;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.repository.ProductRepository;
import com.example.springecommerce.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        Product product1 = new Product();
        Product product2 = new Product();
        Category category = new Category();
        category.setId(1);
        product1.setId(1L);
        product2.setId(2L);
        product1.setCategory(category);
        product2.setCategory(category);
        products = Stream.of(product1, product1).collect(Collectors.toList());
    }

    @Test
    void saveProduct_ReturnProduct_WhenOk() {
        ProductDTO productDTO = new ProductDTO();
        Mockito.when(productRepository.save(Mockito.anyObject())).thenReturn(products.get(0));
        Assertions.assertThat(new ProductDTO(products.get(0)))
                .usingRecursiveComparison()
                .isEqualTo(productService.saveProduct(productDTO));
    }

    @Test
    void getProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(products);
        assertEquals(2, productService.getProducts().size());
    }

    @Test
    void getProductsByCategoryId() {
        Mockito.when(productRepository.findByCategoryId(Mockito.anyInt())).thenReturn(products);
        assertEquals(2, productService.getProductsByCategoryId(Mockito.anyInt()).size());
    }

    @Test
    void getProduct_ReturnProduct_WhenFound() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(products.get(0)));
        Assertions.assertThat(new ProductDTO(products.get(0)))
                .usingRecursiveComparison()
                .isEqualTo(productService.getProduct(Mockito.anyInt()));
    }

    @Test
    void updateProduct_ReturnProduct_WhenOk() {
        ProductDTO productDTO = new ProductDTO(products.get(0));
        productDTO.setName("Chai lavie 500ML");
        Mockito.when(categoryRepository.getById(Mockito.anyObject())).thenReturn(products.get(0).getCategory());
        Mockito.when(productRepository.getById(Mockito.anyObject())).thenReturn(products.get(0));
        Mockito.when(productRepository.save(Mockito.anyObject())).thenReturn(products.get(0));
        Assertions.assertThat(productDTO)
                .usingRecursiveComparison()
                .ignoringFields("updatedDate")
                .isEqualTo(productService.updateProduct(productDTO));
    }

    @Test
    void deleteProduct_WhenOk() {
        productService.deleteProduct(Mockito.anyLong());
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}