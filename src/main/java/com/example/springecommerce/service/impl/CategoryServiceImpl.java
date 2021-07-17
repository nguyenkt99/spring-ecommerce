package com.example.springecommerce.service.impl;

import com.example.springecommerce.dto.CategoryDTO;
import com.example.springecommerce.entity.Category;
import com.example.springecommerce.exception.NotFoundException;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category savedCategory = categoryRepository.save(categoryDTO.toEntity());
        return new CategoryDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category does not exists"));
        return new CategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
//        Category category = categoryRepository.findById(categoryDTO.getId()).get();
        Category category = categoryRepository.getById(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryDTO(savedCategory);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
