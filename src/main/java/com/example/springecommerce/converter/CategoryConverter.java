package com.example.springecommerce.converter;

import com.example.springecommerce.dto.CategoryDTO;
import com.example.springecommerce.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        if(categoryDTO.getId() != null) {
            category.setId(categoryDTO.getId());
        }
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return category;
    }

    public Category toEntity(CategoryDTO categoryDTO, Category category) {
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}
