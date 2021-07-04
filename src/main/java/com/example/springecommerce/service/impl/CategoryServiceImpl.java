package com.example.springecommerce.service.impl;

import com.example.springecommerce.converter.CategoryConverter;
import com.example.springecommerce.dto.CategoryDTO;
import com.example.springecommerce.entity.Category;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryConverter categoryConverter;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDto) {
        Category category = categoryConverter.toEntity(categoryDto);
        return categoryConverter.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categoryDtos = new ArrayList<>();
        List<Category> list = categoryRepository.findAll();
        for(Category category : list) {
            categoryDtos.add(categoryConverter.toDto(category));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDTO getCategory(int id) {
        return categoryConverter.toDto(categoryRepository.getById(id));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDto) {
        Category oldCategory = categoryRepository.getById(categoryDto.getId());
        Category newCategory = categoryConverter.toEntity(categoryDto, oldCategory);
        return categoryConverter.toDto(categoryRepository.save(newCategory));
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
