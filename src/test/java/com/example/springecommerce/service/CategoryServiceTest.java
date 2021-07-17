package com.example.springecommerce.service;

import com.example.springecommerce.dto.CategoryDTO;
import com.example.springecommerce.entity.Category;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.service.impl.CategoryServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private List<Category> categories;

    @BeforeEach
    void setUp() {
        Category category1 = new Category();
        Category category2 = new Category();

        category1.setId(1);
        category2.setId(2);
        categories = Stream.of(category1, category2).collect(Collectors.toList());
    }

    @Test
    void saveCategory_ReturnCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        Mockito.when(categoryRepository.save(Mockito.anyObject())).thenReturn(categories.get(0));
        Assertions.assertThat(new CategoryDTO(categories.get(0)))
                .usingRecursiveComparison()
                .isEqualTo(categoryService.saveCategory(categoryDTO));
    }

    @Test
    void getCategories_ReturnCategories_WhenFound() {
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        assertEquals(2, categoryService.getCategories().size());
    }

    @Test
    void getCategory_ReturnCategory_WhenFound() {
        Mockito.when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(categories.get(0)));
        Assertions.assertThat(new CategoryDTO(categories.get(0)))
                .usingRecursiveComparison()
                .isEqualTo(categoryService.getCategory(Mockito.anyInt()));
    }

    @Test
    void updateCategory_ReturnCategory() {
        CategoryDTO categoryDTO = new CategoryDTO(categories.get(0));
        categoryDTO.setName("lavie");
        Mockito.when(categoryRepository.getById(Mockito.anyInt())).thenReturn(categories.get(0));
        Mockito.when(categoryRepository.save(Mockito.anyObject())).thenReturn(categories.get(0));
        Assertions.assertThat(categoryDTO)
                .usingRecursiveComparison()
                .isEqualTo(categoryService.updateCategory(categoryDTO));
    }

    @Test
    void deleteCategory() {
        categoryService.deleteCategory(Mockito.anyInt());
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }
}